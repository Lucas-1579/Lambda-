package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entitie.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Employee> list = new ArrayList<>();		
		
		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			String line = br.readLine();
			while(line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
			System.out.print("Enter salary: ");
			double salary = sc.nextDouble();
			System.out.println("Email of people whose salary is more than " + salary);
			
			List<String> list2 = list.stream()
					.filter(x -> x.getSalary() >= salary)
					.map(x -> x.getEmail())
					.sorted()
					.collect(Collectors.toList());
			
			double total = list.stream().filter(x -> x.getName().toUpperCase().charAt(0) == 'M')
					.map(x -> x.getSalary())
					.reduce(0.0, (x,y) -> x + y);
			
			list2.forEach(System.out::println);
			System.out.println("Sum of salary of people whose name starts with 'M': "
					+ String.format("%.2f", total));
			
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
		
		sc.close();

	}

}
