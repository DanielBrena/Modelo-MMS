import mms.ModeloMMS;


public class Main {
	public static void main(String[] args){
		ModeloMMS mms = new ModeloMMS(14.0,10.0,2);
		
		
		System.out.println("Calcular Ro");
		System.out.println(mms.calcularRo());
		
		System.out.println("Calcular P0");
		System.out.println(mms.calcularP0());
		
		System.out.println("Calcular Lq");
		System.out.println(mms.calcularLq());
		
		System.out.println("Calcular L");
		System.out.println(mms.calcularLOpcion1());
		
		System.out.println("Calcular Wq");
		System.out.println(mms.calcularWq());
	}
}
