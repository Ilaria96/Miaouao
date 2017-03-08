package it.dstech.main;

import java.util.List;
import java.util.Scanner;

import it.dstech.config.HibernateUtil;
import it.dstech.dao.HibernateDao;
import it.dstech.model.Student;

public class HibernateMain {

	private static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		HibernateDao.getSession();
		boolean exit = true;

		do {
			System.out.println("1 - Crea studente");
			System.out.println("2 - Lista studenti");
			System.out.println("3 - Update studente");
			System.out.println("4 - Delete studente");
			System.out.println("5 - Termina programma");

			int scelta = in.nextInt();
			in.nextLine();

			switch (scelta) {
			case 1:

				createStudent();
				break;
			case 2:

				getAllStudents();
				break;
			case 3:

				updateStudent();
				break;
			case 4:

				deleteStudent();
				break;
			case 5:
				HibernateDao.closeSession();
				HibernateUtil.shutdown();
				exit = false;
				
				
			}
		} while (exit);

	}

	private static void createStudent() {

		Student student = new Student();
		System.out.println("Inserisci nome");
		student.setFirstName(in.nextLine());
		System.out.println("Inserisci cognome");
		student.setLastName(in.nextLine());
		System.out.println("Inserisci sezione");
		student.setSection(in.nextLine());

		System.out.println("Studente creato: " + HibernateDao.persist(student));
	}

	@SuppressWarnings("unchecked")
	private static List<Student> getAllStudents() {

		List<Student> listStudents = HibernateDao.getAll("FROM Student s ORDER BY s.firstName ASC");

		int id = 1;
		for (Student student : listStudents) {
			System.out.println(id + " - " + student);
			id++;
		}
		return listStudents;
	}

	private static void updateStudent() {

		List<Student> allStudents = getAllStudents();

		System.out.println("Scegli lo studente da aggiornare");

		int scelta = in.nextInt();
		in.nextLine();

		Student student = allStudents.get(scelta - 1);

		System.out.println("Modificare il nome? y/n");
		if (in.nextLine().equals("y")) {
			System.out.println("Inserisci il nome?");
			student.setFirstName(in.nextLine());
		}
		System.out.println("Modificare il cognome? y/n");
		if (in.nextLine().equals("y")) {
			System.out.println("Inserisci il cognome?");
			student.setLastName(in.nextLine());
		}
		System.out.println("Modificare la sezione? y/n");
		if (in.nextLine().equals("y")) {
			System.out.println("Inserisci la sezione?");
			student.setSection(in.nextLine());
		}

		System.out.println("Utente aggiornato: " + HibernateDao.update(student));

	}

	private static void deleteStudent() {

		List<Student> allStudents = getAllStudents();

		System.out.println("Scegli lo studente da cancellare");

		int scelta = in.nextInt();
		in.nextLine();

		System.out.println("Utente eliminato: " + HibernateDao.delete(allStudents.get(scelta - 1)));

	}

}
