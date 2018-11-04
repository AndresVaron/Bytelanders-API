package entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ClienteCompraEntity extends BaseEntity{

	private String cliente;
	
	private boolean comprado;
	
	public ClienteCompraEntity() {
	
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	public void setComprado(boolean comprado) {
		this.comprado = comprado;
	}
	
	public boolean isComprado() {
		return comprado;
	}
	
	
	
}
