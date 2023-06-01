// Copyright (C) 2020
// All rights reserved
package Estimator;

public class VacationPackage {
	
	public String destination;
	public int number_travelers;
	public int duration;
	public double cost ;

	
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getNumber_travelers() {
		return number_travelers;
	}
	public void setNumber_travelers(int number_travelers) {
		this.number_travelers = number_travelers;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	/**
     * Crea una nueva instancia de VacationPackage con el destino, número de viajeros y duración especificados.
     *
     * @param destination el destino del paquete de vacaciones
     * @param number_travelers el número de viajeros
     * @param duration la duración del paquete de vacaciones en días
     */
	public VacationPackage(String destination, int number_travelers, int duration) {
		if(number_travelers <= 80) {
			this.destination = destination;
			this.number_travelers = number_travelers;
			this.duration = duration;
			this.cost = 1000;
			
		}
		else {
			System.out.println("no se puede");
		}
	}
	 /**
     * Verifica el destino del paquete de vacaciones y actualiza el costo según corresponda.
     *
     * @param paquete1 el paquete de vacaciones a verificar
     */
	public void verifySpot(VacationPackage paquete1) {
		if(paquete1.getDestination().equals("Paris")) {
			paquete1.setCost(paquete1.getCost() + 500);
		}
		else if(paquete1.getDestination().equals("New York City")) {
			paquete1.setCost(paquete1.getCost() + 600);
		}
	}
	/**
     * Verifica el descuento aplicable al paquete de vacaciones según el número de viajeros y actualiza el costo.
     *
     * @param paquete1 el paquete de vacaciones a verificar
     */
	 public void verifyDiscount(VacationPackage paquete1 ) {
		 if(paquete1.getNumber_travelers() > 4 && paquete1.getNumber_travelers()<10) {
			 paquete1.setCost(paquete1.getCost() - paquete1.getCost()*0.1);
		 }
		 else if(paquete1.getNumber_travelers() >=10) {
			 paquete1.setCost(paquete1.getCost() - paquete1.getCost()*0.2);
		 }
	 }
	 /**
	     * Verifica la penalización aplicable al paquete de vacaciones según la duración y el número de viajeros, y actualiza el costo.
	     *
	     * @param paquete1 el paquete de vacaciones a verificar
	     */
	 public void verifyPenalty(VacationPackage paquete1 ) {
		if(paquete1.getDuration()<10) {
			paquete1.setCost(paquete1.getCost()+200);
		}
		else if(paquete1.getDuration()> 30 || paquete1.getNumber_travelers() == 2) {
			paquete1.setCost(paquete1.getCost()-200);
		}
	 }
	@Override
	public String toString() {
		return "destination= " + destination + ", number_travelers= " + number_travelers + ", duration= "
				+ duration + ", cost =" + cost ;
	}
	 


}
