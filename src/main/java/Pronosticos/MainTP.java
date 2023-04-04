package Pronosticos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainTP {

	public static void main(String[] args) {
		
		// leer archivos resultados
		Collection<Partido> partidos =new ArrayList<Partido>();
		
		Path pathResultados = Paths.get(args[0]);
		List<String> lineasResultados=null;
		try {
			lineasResultados = Files.readAllLines(pathResultados);
		} catch (IOException e) {
			System.out.println("no se pudo leer la linea de resultados");
		//con esto me imprime la excepcion que es la e
			System.out.println(e.getMessage());
			System.exit(1);// solo se debe hacer en el main
		}
		boolean primera=true;
	    for(String lineaResultado: lineasResultados) {
	    	if(primera) {
	    		primera=false;
	    	
	    	}else {
	    		String[] campos= lineaResultado.split(",");
	    		Equipo equipo1= new Equipo(campos[1]);
	    		Equipo equipo2= new Equipo(campos[3]);  
	    	    Partido partido= new Partido(equipo1,equipo2);
	    		
	    		partido.setGolesEq1(Integer.parseInt(campos[1]));
	    		partido.setGolesEq2(Integer.parseInt(campos[2]));
	    		partidos.add(partido);	
	         	System.out.println(lineaResultado);
	         }
	    		
		// leer pronosticos
	    	int puntos=0;
	    	
	    	Path pathPronostico = Paths.get(args[1]);
	    	List<String> lineasPronostico=null;
			try {
				lineasPronostico = Files.readAllLines(pathPronostico);
			} catch (IOException e) {
				System.out.println("no se pudo leer la linea de pronosticos");
			//con esto me imprime la excepcion que es la e
				System.out.println(e.getMessage());
				System.exit(1);// solo se debe hacer en el main	
	    	
              }
		 primera=true;
		    for(String lineaPronostico: lineasPronostico) {
		    	if(primera) {
		    		primera=false;
		    	
		    	}else {
		    		String[] campos=lineaPronostico.split(",");
		    		Equipo equipo1 = new Equipo(campos[0]);
		    		Equipo equipo2 = new Equipo(campos[4]);
		    		
		    		Partido partido= null;
		    		for(Partido partidoCol: partidos) {
		    			if(partidoCol.getEquipo1().getNombre()
		    					.equals(equipo1.getNombre())
		    					&&partidoCol.getEquipo2().getNombre()
		    					.equals(equipo2.getNombre()));{
		    			
		    			partido = partidoCol;
		    					    				
		    			}
		    		
		    		}
		    		Equipo equipo=null;
		    		String resultado= null;
		    		if ("X".equals(campos[1])) {
		    			equipo = equipo1;
		    		    resultado="ganador";
		    		}   
		    		    if ("X".equals(campos[2])) {
			    			equipo = equipo1;
			    		    resultado="empate";
		    		}
		    		    if ("X".equals(campos[3])) {
			    			equipo = equipo1;
			    		    resultado="perdedor";
			    		    
		    		}
		    		Pronostico pronostico= new Pronostico(partido, equipo, resultado);
		    	 	//sumar los puntos correspondientes
		    	    puntos+=pronostico.puntos();
		    	
		    	}
	        }
	   	
		// mostrar los puntos
	    System.out.println("los puntos obtenidos por el usuario fueron:");
        System.out.println(puntos);	   
	}
	 }
}




