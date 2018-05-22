package cmu.conditional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.sat4j.specs.IVec;
import org.sat4j.specs.IVecInt;
import org.sat4j.specs.IteratorInt;

import de.fosd.typechef.featureexpr.FeatureExpr;
import de.fosd.typechef.featureexpr.FeatureExprFactory;
import de.fosd.typechef.featureexpr.SingleFeatureExpr;
import de.fosd.typechef.featureexpr.bdd.BDDFeatureExpr;
import de.fosd.typechef.featureexpr.bdd.BDDFeatureModel;
import net.sf.javabdd.BDD;
import scala.Tuple2;

/**
 * Representation of a values that depend on {@link FeatureExpr}.
 * 
 * @author Jens Meinicke
 *
 */
public abstract class Conditional<T> {

	public static BDDFeatureModel fm = null;
	public static BDDFeatureExpr bddFM;
	public static final Map<String, SingleFeatureExpr> features = new HashMap<>();
	
	private static final Map<BDD, Boolean> cacheIsSat = new HashMap<>();
	
	private static final Map<BDD, Map<BDD, FeatureExpr>> cacheAnd = new HashMap<>();
	
	private static final Map<BDD, FeatureExpr> cacheNot = new HashMap<>();
	
	public static void setFM(final String fmfile) {
		resetCounters();
		
		cacheIsSat.clear();
		features.clear();
		cacheNot.clear();
		cacheAnd.clear();
		
		fm = (BDDFeatureModel) (fmfile.isEmpty() ? null : FeatureExprFactory.bdd().featureModelFactory().createFromDimacsFile(fmfile));
		if (fm != null) {
			createBDDFeatureModel();
		} else {
			bddFM = (BDDFeatureExpr) FeatureExprFactory.bdd().True();
		}
	}
	
	/**
	 * Creates a BDD from the given feature model.
	 */
	private static void createBDDFeatureModel() {
		@SuppressWarnings("rawtypes")//Gradle compiler bug
		final IVec clauses = fm.clauses();
		final scala.collection.immutable.Map<String, Object> vars = fm.variables();
		java.util.Map<Integer, String> map = new HashMap<>();
		for (Tuple2<String, Object> tuple : scala.collection.JavaConversions.asJavaCollection(vars)) {
			map.put((Integer)tuple._2, tuple._1);
		}
		final int size = clauses.size();
		FeatureExpr construction = FeatureExprFactory.True();
		for (int i = 0; i < size; i++) {
			IVecInt c = (IVecInt) clauses.get(i);
			IteratorInt iterator = c.iterator();
			FeatureExpr clause = FeatureExprFactory.False();
			while (iterator.hasNext()) {
				int value = iterator.next();
				boolean selection2 = value > 0;
				String feature = map.get(Math.abs(value));
				SingleFeatureExpr featureExpr = features.get(feature);
				if (featureExpr == null) {
					featureExpr = FeatureExprFactory.createDefinedExternal(feature);
					features.put(featureExpr.feature(), featureExpr);
				}
				if (selection2) {
					clause = clause.or(featureExpr);
				} else {
					clause = clause.orNot(featureExpr);
				}
			}
			construction = construction.and(clause);
		}
		bddFM = (BDDFeatureExpr) construction;
	}
	
	public static SingleFeatureExpr createFeature(String fname) {
		final SingleFeatureExpr feature = FeatureExprFactory.createDefinedExternal("CONFIG_" + fname);
		features.put(fname, feature);
		return feature;
	}
	
	public static boolean cache = false;
	
	private static void resetCounters() {
		notCalls = 0;
		notTime = 0;
		andCalls = 0;
		andTime = 0;
	}
	
	private static int notCalls = 0;
	private static long notTime = 0;
	
	private static int andCalls = 0;
	private static long andTime = 0;
	
	private static int contrCalls = 0;
	private static long contrTime = 0;
	
	private static int satCalls = 0;
	private static long satTime = 0;
	
	public static FeatureExpr not(FeatureExpr a) {
		notCalls++;
		long start = System.nanoTime();
		try {
			if (cache) {
				return cacheNot.computeIfAbsent(((BDDFeatureExpr)a).bdd(), x -> a.not());
			} else {
				return a.not();
			}
		} finally {
			long end = System.nanoTime();
			long time = end - start;
			notTime += time;
		}
	}
	
	public static FeatureExpr orNot(final FeatureExpr a, final FeatureExpr b) {
		if (((BDDFeatureExpr)a).bdd() == ((BDDFeatureExpr)b).bdd()) {
			return FeatureExprFactory.True();
		}
		return not(and(not(a), b));
	}
	
	public static FeatureExpr or(final FeatureExpr a, final FeatureExpr b) {
		if (((BDDFeatureExpr)a).bdd() == ((BDDFeatureExpr)b).bdd()) {
			return a;
		}
		return not(and(not(a), not(b)));
	}
	
	public static FeatureExpr andNot(final FeatureExpr a, final FeatureExpr b) {
		return and(a, not(b));
	}
	
	public static FeatureExpr and(final FeatureExpr a, final FeatureExpr b) {
		andCalls++;
		long start = System.nanoTime();
		try {
			if (cache) {
		
			BDD bddA = ((BDDFeatureExpr)a).bdd();
			BDD bddB = ((BDDFeatureExpr)b).bdd();
			if (bddA == bddB) {
				return a;
			}
			if (bddA.hashCode() > bddB.hashCode()) {
				bddA = bddB;
				bddB = ((BDDFeatureExpr)a).bdd();
			}
			Map<BDD, FeatureExpr> aMap = cacheAnd.get(bddA);
			if (aMap == null) {
				aMap = new HashMap<>();
				cacheAnd.put(bddA, aMap);
			}
			return aMap.computeIfAbsent(bddB, x -> a.and(b));
			}
			else {
				return a.and(b);
			}
		} finally {
			long end = System.nanoTime();
			long time = end - start;
			andTime += time;
		} 
	}
	
	public static boolean equals(FeatureExpr a, FeatureExpr b) {
		if (a == b) return true;
		return ((BDDFeatureExpr)a).bdd().equals(((BDDFeatureExpr)b).bdd());
	}
	
	public static boolean equivalentTo(FeatureExpr a, FeatureExpr b) {
		return a.equals(b) || isTautology(equiv(a, b));
	}
	
	private static FeatureExpr equiv(FeatureExpr a, FeatureExpr b) {
		return or(and(a, b), and(not(a), not(b)));
	}

	public static final boolean isContradiction(final FeatureExpr f) {
		contrCalls++;
		long start = System.nanoTime();
		try {
			if (cache) {
				final BDD bdd = ((BDDFeatureExpr)f).bdd();
				final Boolean value = cacheIsSat.get(bdd);
				if (value != null) {
					return !value;
				}
				return !cacheIsSat.computeIfAbsent(bdd, x -> f.isSatisfiable(fm));
			} else {
				return !f.isSatisfiable(fm);
			}
		} finally {
			long end = System.nanoTime();
			long time = end - start;
			contrTime += time;
		} 
	}

	public static final boolean isTautology(final FeatureExpr f) {
		satCalls++;
		long start = System.nanoTime();
		try {
			if (cache) {
				return !cacheIsSat.computeIfAbsent(((BDDFeatureExpr)not(f)).bdd(), x -> f.not().isSatisfiable(fm));
			} else { 
				return f.isTautology(fm);
			}
		} finally {
			long end = System.nanoTime();
			long time = end - start;
			satTime += time;
		} 
	}

	public abstract T getValue();

	public abstract T getValue(boolean ignore);

	// def map[U](f: T => U): Conditional[U] = mapr(x => One(f(x)))
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <U> Conditional<U> map(final Function<T, U> f) {
		return mapfr(null, (FeatureExpr c, T x) -> new One(f.apply(x)));
	}

	// def mapr[U](f: T => Conditional[U]): Conditional[U] = mapfr(True, (c, x)
	// => f(x))
	public <U> Conditional<U> mapr(final Function<T, Conditional<U>> f) {
		return mapfr(FeatureExprFactory.True(), (FeatureExpr c, T x) -> f.apply(x));
	}

	// def mapf[U](inFeature: FeatureExpr, f: (FeatureExpr, T) => U):
	// Conditional[U] = mapfr(inFeature, (c, x) => One(f(c, x)))
	public <U> Conditional<U> mapf(FeatureExpr inFeature, final BiFunction<FeatureExpr, T, Conditional<U>> f) {
		return mapfr(inFeature, f::apply);
	}

	public void mapf(FeatureExpr inFeature, final BiConsumer<FeatureExpr, T> f) {
		mapfr(inFeature, f::accept);
	}

	// def mapfr[U](inFeature: FeatureExpr, f: (FeatureExpr, T) =>
	// Conditional[U]): Conditional[U]
	public abstract <U> Conditional<U> mapfr(FeatureExpr inFeature, BiFunction<FeatureExpr, T, Conditional<U>> f);

	public abstract void mapfr(FeatureExpr inFeature, BiConsumer<FeatureExpr, T> f);

	public abstract Conditional<T> simplifyValues();

	public Conditional<T> simplify() {
		return simplify(FeatureExprFactory.True());
	}

	public abstract Conditional<T> simplify(FeatureExpr ctx);

	public final List<T> toList() {
		final List<T> list = new NoCheckArrayList(size());
		toList(list);
		return list;
	}
	
	final class NoCheckArrayList extends ArrayList<T> {
		private static final long serialVersionUID = 1L;

		public NoCheckArrayList(int initialSize) {
			super(initialSize);
		}
		
		@Override
		public void ensureCapacity(int minCapacity) {
			// avoid checking for capacity
		}
	}
	
	protected abstract void toList(List<T> list);

	public Map<T, FeatureExpr> toMap() {
		Map<T, FeatureExpr> map = new HashMap<>();
		toMap(FeatureExprFactory.True(), map);
		return map;
	}

	protected abstract void toMap(FeatureExpr f, Map<T, FeatureExpr> map);

	@Override
	public abstract Conditional<T> clone() throws CloneNotSupportedException;

	public static String getCTXString(final FeatureExpr originalContext) {
		FeatureExpr ctx = simplifyCondition(originalContext);
		boolean oneSample = ctx instanceof BDDFeatureExpr && ((BDDFeatureExpr) ctx).bdd().pathCount() > 1000;
		if (oneSample) {
			ctx = new BDDFeatureExpr(((BDDFeatureExpr) ctx).bdd().satOne());
		}
		String context = ctx.toString().replaceAll("CONFIG_", "").replaceAll("__SELECTED_FEATURE_", "").replaceAll("def\\(", "(");
		context = trimExpression(context);
		if (oneSample) {
			context = context + " | ...";
		} else if ((context.length() > 300 && context.contains("|"))) {
			context = (context.substring(0, context.indexOf('|')) + " | ...");
		}

		return context;
	}

	private static final String PATTERN = "\\((\\w*)\\)";

	/**
	 * Replaces all "(Feature)" by "Feature"
	 */
	private static String trimExpression(String ctx) {
		return ctx.replaceAll(PATTERN, "$1");
	}


	public static FeatureExpr simplifyCondition(FeatureExpr ctx) {
		return ctx.simplify(bddFM);
	}
	
	public static FeatureExpr simplifyCondition(FeatureExpr ctx, FeatureExpr additionalConmstraint) {
		return ctx.simplify(bddFM.and(additionalConmstraint));
	}

	public abstract int size();

	public boolean isOne() {
		return false;
	}
	
	public static void printstats() {
		System.out.println("notcalls:" + notCalls);
		System.out.println("nottime:" + notTime/1_000_000 + "." + notTime%1_000_000 + "ms");
		System.out.println("notsize:" + cacheNot.size());
		
		System.out.println("andcalls:" + andCalls);
		System.out.println("andtime:" + andTime/1_000_000 + "." + andTime%1_000_000 + "ms");
		System.out.println("andsize:" + cacheAnd.size());

		System.out.println("contrcalls:" + contrCalls);
		System.out.println("contrtime:" + contrTime/1_000_000 + "." + contrTime%1_000_000 + "ms");
		
		System.out.println("satcalls:" + satCalls);
		System.out.println("contrtime:" + satTime/1_000_000 + "." + satTime%1_000_000 + "ms");
		System.out.println("SATsize:" + cacheIsSat.size());
		
		System.out.println("time:" + (notTime + andTime + contrTime +satTime) / 1_000_000);
	}
	
}
