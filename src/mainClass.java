import java.io.File;

public class mainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VowelSound st = new VowelSound();
		st.speak.add(st.vowel(121, 652,997,2538,3486,1));
		st.speak.play(st.device);
		st.speak.save(st.device.getFormat(), new File("vowel.wav"));
		System.exit(0);
	}

}
