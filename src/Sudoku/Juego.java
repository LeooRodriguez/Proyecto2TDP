package Sudoku;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
/**
 * Clase Juego la cual modela un Sudoku.
 * @author Leonardo Rodríguez
 *
 */



public class Juego {
	private Celda [][] tablero; //Tablero de sudoku
	private int cantFilas;
	private int [][]m_texto;//matriz de elementos del archivo de texto
	private boolean esValidoArch;

	public Juego() {
		File file= new File("Orden_Sudoku.txt");
		this.cantFilas = 9;
		tablero = new Celda[this.cantFilas][this.cantFilas];
		m_texto = new int[this.cantFilas][this.cantFilas];
		Scanner s=null;
		int num;
		boolean valido=true;
		boolean existeArch=file.exists();
		if(existeArch) {
			try {
				valido=verificar_archivo();
				esValidoArch=valido;
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				s=new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			if(valido) {
				for (int i =0; i<cantFilas; i++) {
					for (int j =0; j<cantFilas; j++) {
						num=s.nextInt();
						m_texto[i][j]=num;
					}
				}
			}
			if(valido) {//Inicializo el tablero en base al archivo de texto.
				for (int i =0; i<cantFilas; i++) {
					for (int j =0; j<cantFilas; j++) {
						tablero[i][j]=new Celda(i,j,true);
						Random rand = new Random();
						int valor = 0;
						int value = rand.nextInt(3); 
						if(value==0 || value == 1) {
							valor = m_texto[i][j]-1;	
							tablero[i][j].setValor(valor);	
						}
					}
				}
			}
			else
				tablero=null;
		}
		else
			System.out.println("ERROR: ARCHIVO INEXISTENTE, VERIFIQUE RUTA");
	}

	/**
	 * Actualiza la imagen de la celda.
	 * @param c Celda a actualizar.
	 */
	public void accionar(Celda c) {
		c.actualizar();
	}

	/**
	 * Devuelve la celda correspondiente en la posicion i,j.
	 * @param i Fila de la celda.
	 * @param j Columna de la celda.
	 * @return	celda Celda en la posicion i,j. 
	 */
	public Celda getCelda(int i, int j) {
		return this.tablero[i][j];
	}

	/**
	 * Devuelve la cantidad de filas del juego.
	 * @return cantFilas 
	 */
	public int getCantFilas() {
		return this.cantFilas;
	}

	/**
	 * Verifica si una celda cumple con las propiedades de una celda de Sudoku.
	 * @param c Celda a verficar.
	 * @return Verdadero si cumple, Falso caso contrario.
	 */
	public boolean verificar(Celda c) {
		boolean toRet=true;
		boolean cf,cc,cs;
		pintarInvalidas();
		cf=cumpleFilas(c);
		cc=cumpleCol(c);
		cs=cumpleSector(c);
		toRet=cf&&cc&&cs;
		pintarValidas();
		return toRet;
	}

	/**
	 * Resetea las celdas a válidas si verifican las propiedades de una celda de Sudoku.
	 */
	public void reset() {
		for(int i=0;i<cantFilas;i++)
			for(int j=0;j<cantFilas;j++) {
				if(verificar(tablero[i][j]))
					tablero[i][j].setEsValida(true);
			}
	}

	/**
	 * Verifica que todas las celdas sean válidas.
	 * @return Verdadero si TODAS las celdas son válidas, Falso caso contrario.
	 */
	public boolean ganar() {
		boolean gano=true;
		for(int i=0;i<cantFilas;i++)
			for(int j=0;j<cantFilas;j++) {
				if(tablero[i][j].getValor()==null||!tablero[i][j].isEsValida())
					gano=false;
			}
		return gano;
	}

	/**
	 * Devuelve si el archivo de texto es válido..
	 * @return Verdadero si es válido, Falso caso contrario.
	 */
	public boolean getValidoArch() {
		return esValidoArch;
	}

	/**
	 * Verifica en base a una celda que en esa misma fila no haya ninguna otra celda con el mismo elemento.
	 * @param c Celda a verificar.
	 * @return Verdadero si no se repiten los elementos, Falso caso contrario.
	 */
	private boolean cumpleFilas(Celda c) {
		boolean toRet=true;
		int fila=c.getFila();	
		for(int i = 0; i < cantFilas; i++) {
			if(tablero[fila][i].getValor() != null) {
				if(c.getValor() == tablero[fila][i].getValor()&&!(tablero[fila][i].equals(c))){
					tablero[fila][i].setEsValida(false);
					toRet=false;
				}
			}
		}
		return toRet;
	}

	/**
	 * Verifica en base a una celda que en esa misma columna no haya ninguna otra celda con el mismo elemento.
	 * @param c Celda a verificar.
	 * @return Verdadero si no se repiten los elementos, Falso caso contrario.
	 */
	private boolean cumpleCol(Celda c) {
		boolean toRet=true;
		int col=c.getCol();
		for(int i = 0; i < cantFilas; i++) {
			if(tablero[i][col].getValor() != null) {
				if(c.getValor() == tablero[i][col].getValor()&&!(tablero[i][col].equals(c))){
					tablero[i][col].setEsValida(false);
					toRet=false;
				}
			}
		}
		return toRet;
	}

	/**
	 * Verifica en base a una celda que en ese mismo cuadrante/sector no haya ninguna otra celda con el mismo elemento.
	 * @param c Celda a verificar.
	 * @return Verdadero si no se repiten los elementos, Falso caso contrario.
	 */
	private boolean cumpleSector(Celda c) {
		boolean toRet=true;
		int fila;
		int col;
		if(c.getFila()<3)//Calculo en base a la posicion de la celda el sector.
			fila=0;
		else {
			if(c.getFila()<6)
				fila=3;
			else
				fila=6;
		}
		if(c.getCol()<3)
			col=0;
		else {
			if(c.getCol()<6)
				col=3;
			else
				col=6;
		}
		for(int i=fila;i<fila+3;i++)
			for(int j=col;j<col+3;j++) {
				if(tablero[i][j].getValor() != null) {
					if(c.getValor() == tablero[i][j].getValor()&&!(tablero[i][j].equals(c))){
						tablero[i][j].setEsValida(false);
						toRet=false;
					}
				}
			}
		return toRet;
	}

	/**
	 * Evalúa que el archivo tenga el tamaño correcto y los caracteres correspondientes.
	 * @return Verdadero si el archivo es correcto,Falso caso contrario.
	 */
	@SuppressWarnings("resource")
	private boolean verificar_archivo() throws Exception{
		String nombreFichero = "Orden_Sudoku.txt";
		boolean esValido=true;
		boolean cumpleRango=true;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(nombreFichero));
			String texto = br.readLine();
			while(texto != null)
			{
				for(int i=0;i<texto.length()&&esValido&&cumpleRango;i++) {
					if(!esNum(texto.charAt(i))) {
						esValido=false; 
					}
					if(texto.length()!=cantFilas*2-1)
						cumpleRango=false;
					if(!esValido)
						throw new Exception("ERROR:ELEMENTOS DEL SUDOKU INVALIDOS");
					if(!cumpleRango)
						throw new Exception("ERROR:TAMAÑO DEL SUDOKU INVALIDO");
				}
				System.out.println(texto);
				texto = br.readLine();
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("Error: Fichero no encontrado");
			System.out.println(e.getMessage());
		}
		catch(Exception e) {
			System.out.println("Error de lectura del fichero");
			System.out.println(e.getMessage());
		}
		finally {
			try {
				if(br != null)
					br.close();
			}
			catch (Exception e) {
				System.out.println("Error al cerrar el fichero");
				System.out.println(e.getMessage());
			}
		}
		return esValido&&cumpleRango;
	}

	/**
	 * Verifica que un caracter sea válido en base a un conjunto reducido de caracteres.
	 * @param c Caracter a verificar.
	 * @return Verdadero si el caracter pertenece a el conjunto, Falso caso contrario.
	 */
	private boolean esNum(char c){
		return c=='1'||c=='2'||c=='3'||c=='4'||c=='5'||c=='6'||c=='7'||c=='8'||c=='9'||c==' ';
	}

	/**
	 * Recorre el tablero y si una celda es invalida la pinta de rojo.
	 */
	private void pintarInvalidas() {
		for(int i=0;i<cantFilas;i++)
			for(int j=0;j<cantFilas;j++) {
				if(!tablero[i][j].isEsValida())
					tablero[i][j].setBackground(Color.red);
			}
	}

	/**
	 * Recorre el tablero y si una celda es valida la pinta de negro.
	 */
	private void pintarValidas() {
		for(int i=0;i<cantFilas;i++)
			for(int j=0;j<cantFilas;j++) {
				if(tablero[i][j].isEsValida())
					tablero[i][j].setBackground(Color.blue);
			}
	}

}