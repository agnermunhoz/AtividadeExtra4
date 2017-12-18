package br.com.fiap.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="paciente")
public class Paciente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CPF", length=11)
	private String cpf;
	@Column(name = "NOME")
	private String nome;
	@Column(name = "DATANASC")
	@Temporal(TemporalType.DATE)
	private Date dataNasc;
	@Column(name = "TELEFONE", length=20)
	private String telefone;
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="paciente")
	private List<Matmed> matmeds = new ArrayList();
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="paciente")
	private List<Procedimento> procedimentos = new ArrayList();
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "agenda_paciente",
			joinColumns = {
					@JoinColumn(name = "PACIENTE_CPF", nullable = false, updatable = false) }, inverseJoinColumns = {
							@JoinColumn(name = "AGENDA_ID", nullable = false, updatable = false) })
	private List<Agenda> agendamentos = new ArrayList<>();
	
	public Paciente() {
		super();
	}

	public Paciente(String cpf, String nome, Date dataNasc, String telefone) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Matmed> getMatmeds() {
		return matmeds;
	}

	public void setMatmeds(List<Matmed> matmeds) {
		this.matmeds = matmeds;
	}

	public List<Procedimento> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<Procedimento> procedimentos) {
		this.procedimentos = procedimentos;
	}

	public List<Agenda> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agenda> agendamentos) {
		this.agendamentos = agendamentos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Paciente [cpf=" + cpf + ", nome=" + nome + ", dataNasc=" + dataNasc + ", telefone=" + telefone + "]";
	}
	
}
