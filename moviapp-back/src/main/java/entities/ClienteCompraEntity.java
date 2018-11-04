package entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ClienteCompraEntity extends BaseEntity{

	@JoinColumn(name = "ClienteEntity")
	private ClienteEntity cliente;
	
	private boolean comprado;
	
	public ClienteCompraEntity() {
	
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
	
	public void setComprado(boolean comprado) {
		this.comprado = comprado;
	}
	
	public boolean isComprado() {
		return comprado;
	}
	
	
	
}
