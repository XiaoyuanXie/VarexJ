package inc;
import gov.nasa.jpf.bdd.TrackWithBDD;
import gov.nasa.jpf.vm.Verify;

public class IncJPF_BDD {

	public static void main(String[] args) {
		Verify.resetInstructionCounter();
		new IncJPF_BDD(Integer.parseInt(args[0]));
	}

	@TrackWithBDD static boolean a1 = Verify.getBoolean();
	@TrackWithBDD static boolean a2 = Verify.getBoolean();
	@TrackWithBDD static boolean a3 = Verify.getBoolean();
	@TrackWithBDD static boolean a4 = Verify.getBoolean();
	@TrackWithBDD static boolean a5 = Verify.getBoolean();
	@TrackWithBDD static boolean a6 = Verify.getBoolean();
	@TrackWithBDD static boolean a7 = Verify.getBoolean();
	@TrackWithBDD static boolean a8 = Verify.getBoolean();
	@TrackWithBDD static boolean a9 = Verify.getBoolean();
	@TrackWithBDD static boolean a10 = Verify.getBoolean();
	@TrackWithBDD static boolean a11 = Verify.getBoolean();
	@TrackWithBDD static boolean a12 = Verify.getBoolean();
	@TrackWithBDD static boolean a13 = Verify.getBoolean();
	@TrackWithBDD static boolean a14 = Verify.getBoolean();
	@TrackWithBDD static boolean a15 = Verify.getBoolean();
	@TrackWithBDD static boolean a16 = Verify.getBoolean();
	@TrackWithBDD static boolean a17 = Verify.getBoolean();
	@TrackWithBDD static boolean a18 = Verify.getBoolean();
	@TrackWithBDD static boolean a19 = Verify.getBoolean();
	@TrackWithBDD static boolean a20 = Verify.getBoolean();
	@TrackWithBDD static boolean a21 = Verify.getBoolean();
	@TrackWithBDD static boolean a22 = Verify.getBoolean();
	@TrackWithBDD static boolean a23 = Verify.getBoolean();
	@TrackWithBDD static boolean a24 = Verify.getBoolean();
	@TrackWithBDD static boolean a25 = Verify.getBoolean();
	@TrackWithBDD static boolean a26 = Verify.getBoolean();
	@TrackWithBDD static boolean a27 = Verify.getBoolean();
	@TrackWithBDD static boolean a28 = Verify.getBoolean();
	@TrackWithBDD static boolean a29 = Verify.getBoolean();
	@TrackWithBDD static boolean a30 = Verify.getBoolean();
	@TrackWithBDD static boolean a31 = Verify.getBoolean();
	@TrackWithBDD static boolean a32 = Verify.getBoolean();
	@TrackWithBDD static boolean a33 = Verify.getBoolean();
	@TrackWithBDD static boolean a34 = Verify.getBoolean();
	@TrackWithBDD static boolean a35 = Verify.getBoolean();
	@TrackWithBDD static boolean a36 = Verify.getBoolean();
	@TrackWithBDD static boolean a37 = Verify.getBoolean();
	@TrackWithBDD static boolean a38 = Verify.getBoolean();
	@TrackWithBDD static boolean a39 = Verify.getBoolean();
	@TrackWithBDD static boolean a40 = Verify.getBoolean();
	@TrackWithBDD static boolean a41 = Verify.getBoolean();
	@TrackWithBDD static boolean a42 = Verify.getBoolean();
	@TrackWithBDD static boolean a43 = Verify.getBoolean();
	@TrackWithBDD static boolean a44 = Verify.getBoolean();
	@TrackWithBDD static boolean a45 = Verify.getBoolean();
	@TrackWithBDD static boolean a46 = Verify.getBoolean();
	@TrackWithBDD static boolean a47 = Verify.getBoolean();
	@TrackWithBDD static boolean a48 = Verify.getBoolean();
	@TrackWithBDD static boolean a49 = Verify.getBoolean();
	@TrackWithBDD static boolean a50 = Verify.getBoolean();
	@TrackWithBDD static boolean a51 = Verify.getBoolean();
	@TrackWithBDD static boolean a52 = Verify.getBoolean();
	@TrackWithBDD static boolean a53 = Verify.getBoolean();
	@TrackWithBDD static boolean a54 = Verify.getBoolean();
	@TrackWithBDD static boolean a55 = Verify.getBoolean();
	@TrackWithBDD static boolean a56 = Verify.getBoolean();
	@TrackWithBDD static boolean a57 = Verify.getBoolean();
	@TrackWithBDD static boolean a58 = Verify.getBoolean();
	@TrackWithBDD static boolean a59 = Verify.getBoolean();
	@TrackWithBDD static boolean a60 = Verify.getBoolean();
	@TrackWithBDD static boolean a61 = Verify.getBoolean();
	@TrackWithBDD static boolean a62 = Verify.getBoolean();
	@TrackWithBDD static boolean a63 = Verify.getBoolean();
	@TrackWithBDD static boolean a64 = Verify.getBoolean();
	@TrackWithBDD static boolean a65 = Verify.getBoolean();
	@TrackWithBDD static boolean a66 = Verify.getBoolean();
	@TrackWithBDD static boolean a67 = Verify.getBoolean();
	@TrackWithBDD static boolean a68 = Verify.getBoolean();
	@TrackWithBDD static boolean a69 = Verify.getBoolean();
	@TrackWithBDD static boolean a70 = Verify.getBoolean();
	@TrackWithBDD static boolean a71 = Verify.getBoolean();
	@TrackWithBDD static boolean a72 = Verify.getBoolean();
	@TrackWithBDD static boolean a73 = Verify.getBoolean();
	@TrackWithBDD static boolean a74 = Verify.getBoolean();
	@TrackWithBDD static boolean a75 = Verify.getBoolean();
	@TrackWithBDD static boolean a76 = Verify.getBoolean();
	@TrackWithBDD static boolean a77 = Verify.getBoolean();
	@TrackWithBDD static boolean a78 = Verify.getBoolean();
	@TrackWithBDD static boolean a79 = Verify.getBoolean();
	@TrackWithBDD static boolean a80 = Verify.getBoolean();
	@TrackWithBDD static boolean a81 = Verify.getBoolean();
	@TrackWithBDD static boolean a82 = Verify.getBoolean();
	@TrackWithBDD static boolean a83 = Verify.getBoolean();
	@TrackWithBDD static boolean a84 = Verify.getBoolean();
	@TrackWithBDD static boolean a85 = Verify.getBoolean();
	@TrackWithBDD static boolean a86 = Verify.getBoolean();
	@TrackWithBDD static boolean a87 = Verify.getBoolean();
	@TrackWithBDD static boolean a88 = Verify.getBoolean();
	@TrackWithBDD static boolean a89 = Verify.getBoolean();
	@TrackWithBDD static boolean a90 = Verify.getBoolean();
	@TrackWithBDD static boolean a91 = Verify.getBoolean();
	@TrackWithBDD static boolean a92 = Verify.getBoolean();
	@TrackWithBDD static boolean a93 = Verify.getBoolean();
	@TrackWithBDD static boolean a94 = Verify.getBoolean();
	@TrackWithBDD static boolean a95 = Verify.getBoolean();
	@TrackWithBDD static boolean a96 = Verify.getBoolean();
	@TrackWithBDD static boolean a97 = Verify.getBoolean();
	@TrackWithBDD static boolean a98 = Verify.getBoolean();
	@TrackWithBDD static boolean a99 = Verify.getBoolean();
	@TrackWithBDD static boolean a100 = Verify.getBoolean();
	
	public IncJPF_BDD(int max) {
		int i = 0;

		int current = 0;
		if (current++ == max) return;
		if (a1) {i++;}if (current++ == max) return;
		if (a2) {i++;}if (current++ == max) return;
		if (a3) {i++;}if (current++ == max) return;
		if (a4) {i++;}if (current++ == max) return;
		if (a5) {i++;}if (current++ == max) return;
		if (a6) {i++;}if (current++ == max) return;
		if (a7) {i++;}if (current++ == max) return;
		if (a8) {i++;}if (current++ == max) return;
		if (a9) {i++;}if (current++ == max) return;
		if (a10){i++;}if (current++ == max) return;
		if (a11){i++;}if (current++ == max) return;
		if (a12){i++;}if (current++ == max) return;
		if (a13){i++;}if (current++ == max) return;
		if (a14){i++;}if (current++ == max) return;
		if (a15){i++;}if (current++ == max) return;
		if (a16){i++;}if (current++ == max) return;
		if (a17){i++;}if (current++ == max) return;
		if (a18){i++;}if (current++ == max) return;
		if (a19){i++;}if (current++ == max) return;
		if (a20){i++;}if (current++ == max) return;
		if (a21){i++;}if (current++ == max) return;
		if (a22){i++;}if (current++ == max) return;
		if (a23){i++;}if (current++ == max) return;
		if (a24){i++;}if (current++ == max) return;
		if (a25){i++;}if (current++ == max) return;
		if (a26){i++;}if (current++ == max) return;
		if (a27){i++;}if (current++ == max) return;
		if (a28){i++;}if (current++ == max) return;
		if (a29){i++;}if (current++ == max) return;
		if (a30){i++;}if (current++ == max) return;
		if (a31){i++;}if (current++ == max) return;
		if (a32){i++;}if (current++ == max) return;
		if (a33){i++;}if (current++ == max) return;
		if (a34){i++;}if (current++ == max) return;
		if (a35){i++;}if (current++ == max) return;
		if (a36){i++;}if (current++ == max) return;
		if (a37){i++;}if (current++ == max) return;
		if (a38){i++;}if (current++ == max) return;
		if (a39){i++;}if (current++ == max) return;
		if (a40){i++;}if (current++ == max) return;
		if (a41){i++;}if (current++ == max) return;
		if (a42){i++;}if (current++ == max) return;
		if (a43){i++;}if (current++ == max) return;
		if (a44){i++;}if (current++ == max) return;
		if (a45){i++;}if (current++ == max) return;
		if (a46){i++;}if (current++ == max) return;
		if (a47){i++;}if (current++ == max) return;
		if (a48){i++;}if (current++ == max) return;
		if (a49){i++;}if (current++ == max) return;
		if (a50){i++;}if (current++ == max) return;
		if (a51){i++;}if (current++ == max) return;
		if (a52){i++;}if (current++ == max) return;
		if (a53){i++;}if (current++ == max) return;
		if (a54){i++;}if (current++ == max) return;
		if (a55){i++;}if (current++ == max) return;
		if (a56){i++;}if (current++ == max) return;
		if (a57){i++;}if (current++ == max) return;
		if (a58){i++;}if (current++ == max) return;
		if (a59){i++;}if (current++ == max) return;
		if (a60){i++;}if (current++ == max) return;
		if (a61){i++;}if (current++ == max) return;
		if (a62){i++;}if (current++ == max) return;
		if (a63){i++;}if (current++ == max) return;
		if (a64){i++;}if (current++ == max) return;
		if (a65){i++;}if (current++ == max) return;
		if (a66){i++;}if (current++ == max) return;
		if (a67){i++;}if (current++ == max) return;
		if (a68){i++;}if (current++ == max) return;
		if (a69){i++;}if (current++ == max) return;
		if (a70){i++;}if (current++ == max) return;
		if (a71){i++;}if (current++ == max) return;
		if (a72){i++;}if (current++ == max) return;
		if (a73){i++;}if (current++ == max) return;
		if (a74){i++;}if (current++ == max) return;
		if (a75){i++;}if (current++ == max) return;
		if (a76){i++;}if (current++ == max) return;
		if (a77){i++;}if (current++ == max) return;
		if (a78){i++;}if (current++ == max) return;
		if (a79){i++;}if (current++ == max) return;
		if (a80){i++;}if (current++ == max) return;
		if (a81){i++;}if (current++ == max) return;
		if (a82){i++;}if (current++ == max) return;
		if (a83){i++;}if (current++ == max) return;
		if (a84){i++;}if (current++ == max) return;
		if (a85){i++;}if (current++ == max) return;
		if (a86){i++;}if (current++ == max) return;
		if (a87){i++;}if (current++ == max) return;
		if (a88){i++;}if (current++ == max) return;
		if (a89){i++;}if (current++ == max) return;
		if (a90){i++;}if (current++ == max) return;
		if (a91){i++;}if (current++ == max) return;
		if (a92){i++;}if (current++ == max) return;
		if (a93){i++;}if (current++ == max) return;
		if (a94){i++;}if (current++ == max) return;
		if (a95){i++;}if (current++ == max) return;
		if (a96){i++;}if (current++ == max) return;
		if (a97){i++;}if (current++ == max) return;
		if (a98){i++;}if (current++ == max) return;
		if (a99){i++;}if (current++ == max) return;
		if (a100){i++;}if (current++ == max) return;
	}
}