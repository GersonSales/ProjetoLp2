package testes.pop.midia;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Optional;

import org.junit.Test;

public class TesteAlgoritmos {

	
	@Test
	public void testeTexto() {
		testeClass();
	}
	
	public void testeClass() {
		
		Class<?> testeAlg =  this.getClass();
		
		Method[] metodos  = testeAlg.getMethods();
		
		for (Method method : metodos) {
			Parameter[] parametro = method.getParameters();
			for (Parameter parameter : parametro) {
				System.out.println(method.getName() + ":  " + parameter.getName());
			}
			System.out.println();
		}
		
	}

	
	public void imprimeStrings(String... string) {
		int cont = 1;
		for (String string2 : string) {
			System.out.println("Nome: " + string2 + "\nNumero: " + cont);
			System.out.println();
			cont ++;
			
		}
		
		
	}
}
