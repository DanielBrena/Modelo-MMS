package mms;

public class ModeloMMS {
	private Double p;
	private Double lamda;
	private Double miu;
	private Integer servidores;
	
	public ModeloMMS(){
		
	}
	
	
	
	public ModeloMMS(Double p, Double lamda, Double miu, Integer servidores) {
		super();
		this.p = p;
		this.lamda = lamda;
		this.miu = miu;
		this.servidores = servidores;
	}

	public ModeloMMS( Double lamda, Double miu, Integer servidores) {
		super();
		this.lamda = lamda;
		this.miu = miu;
		this.servidores = servidores;
	}

	/**
	 * Factor de utilizacion
	 * @return Ro
	 */
	public Double calcularRo(){
		if(this.servidores > 1){
			return (this.lamda) / (this.servidores * this.miu);
		}else{
			return 0.0;
		}
	}
	
	/**
	 * Factor de ocio
	 * @return P0
	 */
	public Double calcularP0(){
		Integer numero_servidores = this.servidores -1;
		Double p0 = 0.0;
		Double resultado_aux = 0.0;
		for(int i = 0; i <= numero_servidores; i++){
			resultado_aux += (Math.pow(this.lamda/this.miu, i)) / this.factorial(i);
		}
		
		p0 = resultado_aux + (Math.pow(this.lamda/this.miu, this.servidores)) / (this.factorial(this.servidores) * (1 - calcularRo()));
		
		return 1/p0;
	}
	
	private Integer factorial(Integer numero){
		if(numero == 0){
			return 1;
		}else{
			return numero * factorial(numero-1);
		}
	}
	
	/**
	 * Probabilidad de que exitan n unidades en el sistema.
	 * @param Numero de unidades
	 * @return Pn
	 */
	public Double calcularPn(Integer n){
		Double pn = 0.0;
		if(n > this.servidores){
			Double nominador =  (Math.pow(this.lamda/this.miu, n)) * this.calcularP0() ;
			Double denominador  =   (this.factorial(this.servidores) * Math.pow(this.servidores, n - this.servidores));
			pn = nominador / denominador;
		}else{
			Double nominador = (Math.pow(this.lamda/this.miu, n)) *  this.calcularP0();
			Integer denominador =  this.factorial(n);
			pn = nominador / denominador;
		}
		return pn;
	}
	
	/**
	 * Tiempo promedio de entidades en el sistema.
	 * @return L
	 */
	public Double calcularLOpcion1(){
		return this.calcularLq() + (this.lamda/this.miu);
	}
	
	/**
	 * Tiempo promedio de entidades en el sistema.
	 * @return L
	 */
	public Double calcularLOpcion2(){
		return this.lamda *  this.calcularW();
	}
	
	/**
	 * Tiempo promedio de entidades en espera;
	 * @return Wq
	 */
	public Double calcularWq(){
		Double wq = 0.0;
		Double nominador = this.calcularLq();
		Double denominador = this.lamda;
		wq = nominador / denominador;
		return wq;
	}
	
	/**
	 * Tiempo promedio de entidades en el sistema.
	 * @return
	 */
	public Double calcularW(){
		return this.calcularWq() + (1 / this.miu);
	}
	
	
	
	/**
	 * Numero promedio de entidades en cola o longitud de la cola.
	 * @return Lq
	 */
	public Double calcularLq(){
		Double lq = 0.0;
		Double numerador = (Math.pow(this.lamda/this.miu, this.servidores)) * (this.calcularP0() * this.calcularRo());
		Double denominador = this.factorial(this.servidores) * Math.pow( 1 - this.calcularRo(),2);
		lq = numerador / denominador;
		
		return lq;
	}
	
	/**
	 * Probabilidades de que un nuevo cliente tenga que esperar.
	 * @return Pw
	 */
	public Double calcularPw(){
		Double pw = 0.0;
		Double aux = Math.pow(this.lamda/this.miu, this.servidores);
		Double nominador = this.calcularP0();
		Double denominador = (this.factorial(this.servidores) * (1 - this.calcularRo()));
		
		pw = aux * (nominador / denominador);
		return pw;
	}
	public Integer getServidores() {
		return servidores;
	}



	public void setServidores(Integer servidores) {
		this.servidores = servidores;
	}
	
	public Double getP() {
		return p;
	}
	public void setP(Double p) {
		this.p = p;
	}
	public Double getLamda() {
		return lamda;
	}
	public void setLamda(Double lamda) {
		this.lamda = lamda;
	}
	public Double getMiu() {
		return miu;
	}
	public void setMiu(Double miu) {
		this.miu = miu;
	}
	
	
}
