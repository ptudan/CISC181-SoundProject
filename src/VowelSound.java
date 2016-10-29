public class VowelSound {
		// Create the tone with frequency freq that
		// lasts for duration seconds.
	Speech speak = new Speech(2);
	SoundDevice device = new SoundDevice();
	
	public double[] sine (double freq, double duration){
		int n = (int) (duration * device.SAMPLING_RATE);
		double[] a = new double[n];
		double dx = 2 * Math.PI * freq / device.SAMPLING_RATE;
		for(int i = 0; i < n; i++){
			a[i] = Math.sin(i * dx);
		}
		return a;
	}
	
	public double[] makef0 (double freq, double duration){
		int n = (int) (duration * device.SAMPLING_RATE);
		double[] a = new double[n];
		double dx = 2 * Math.PI * freq / device.SAMPLING_RATE;
		for(int i = 0; i < n; i++){
			a[i] = Math.sin(i * dx)/(1.0+i);
		}
		return a;
	}
	
	public double[] vowel(int fFreq, int rFreq1, int rFreq2, int rFreq3, int rFreq4, int dur){
		//make pitch wave
		double[] fSin = makef0(fFreq, dur);
		
		//create four sine waves with res freqs
		double[] rSin1 = sine(rFreq1, dur);
		double[] rSin2 = sine(rFreq2, dur);
		double[] rSin3 = sine(rFreq3, dur);
		double[] rSin4 = sine(rFreq4, dur);
		
		//create final wave
		double[] finWave = new double[rSin1.length];
		for(int i = 0; i<rSin1.length; i++){
			finWave[i] = (2*rSin1[i] + 2*rSin2[i] + fSin[i] + rSin3[i] + rSin4[i])/70; 
		}
		return finWave;
	}
	
	
}
