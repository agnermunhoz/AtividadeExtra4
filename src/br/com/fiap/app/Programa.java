package br.com.fiap.app;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.entity.Agenda;
import br.com.fiap.entity.Matmed;
import br.com.fiap.entity.Paciente;
import br.com.fiap.entity.Procedimento;
import br.com.fiap.helper.GenericDao;
import br.com.fiap.util.JpaUtil;

public class Programa {

	public static void main(String[] args) {

		try {
		EntityManager em = JpaUtil.getEntityManager();
		GenericDao<Paciente> pacienteDao = new GenericDao<>(Paciente.class, em);
		
		Paciente paciente = new Paciente("11111111115","João da Silva", new SimpleDateFormat("dd/MM/yyyy").parse("01/03/1975"), "(11)11111-1111");
		
		Procedimento procedimento = new Procedimento("Raio X", 78.5, paciente);
		paciente.getProcedimentos().add(procedimento);

		procedimento = new Procedimento("Endoscopia", 230.0, paciente);
		paciente.getProcedimentos().add(procedimento);
		
		Matmed matmed = new Matmed("Luvas", 10.5, "Luvas cirúrgicas LTDA", paciente);
		paciente.getMatmeds().add(matmed);
		
		matmed = new Matmed("Anestesia", 109.87, "Acme medicamentos SA", paciente);
		paciente.getMatmeds().add(matmed);
		
		Agenda agenda = new Agenda(new SimpleDateFormat("dd/MM/yyyy").parse("10/01/2018"), new SimpleDateFormat("HH:mm:SS").parse("11:30:00"), "Centro cirúgido");
		agenda.getPacientes().add(paciente);
		paciente.getAgendamentos().add(agenda);
		
		pacienteDao.adicionar(paciente);
		
		paciente = null;
		
		paciente = pacienteDao.buscar("11111111111");
		
		System.out.println();
		
		System.out.println(paciente.toString());
		
		paciente.getProcedimentos().forEach(p -> System.out.println(p.toString()));
		paciente.getMatmeds().forEach(p -> System.out.println(p.toString()));
		paciente.getAgendamentos().forEach(p -> System.out.println(p.toString()));
		
		List<Paciente> pacientes = pacienteDao.listar("select p from Paciente p where p.cpf like ?", "%111%");
		
		System.out.println();
		
		pacientes.forEach(p -> { 
			System.out.println(p.toString()); 
			p.getProcedimentos().forEach(i -> System.out.println(i.toString()));
			p.getMatmeds().forEach(i -> System.out.println(i.toString()));
			p.getAgendamentos().forEach(i -> System.out.println(i.toString()));
			System.out.println();
			});
		
		em .close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
