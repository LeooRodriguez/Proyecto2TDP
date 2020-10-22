package Sudoku;



import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Clase Celda la cual modela una celda de un tablero de Sudoku.
 * @author Leonardo Rodríguez
 *
 */
public class Celda extends JButton{

	private static final long serialVersionUID = 1L;
	private Integer valor;
	private int fila,col;
	private ImageIcon grafico;
	private String[] imagenes;
	private boolean esValida;//Valor para validar si cumple las propiedades.

	public Celda(int f,int c,boolean esValida) {
		this.esValida=esValida;
		this.grafico = new ImageIcon();
		this.imagenes = new String[]{"/img/1.png", "/img/2.png","/img/3.png","/img/4.png","/img/5.png","/img/6.png","/img/7.png","/img/8.png","/img/9.png"};
		fila=f;
		col=c;
		this.valor = null;

	}

	/**
	 * Actualiza en base al valor de la celda su imagen.
	 */
	public void actualizar() {
		if (this.valor != null && this.valor + 1 < this.getCantElementos()) {
			this.valor++;
		}else {
			this.valor = 0;
		}
		ImageIcon img= new ImageIcon(this.getClass().getResource(this.imagenes[valor]));
		this.grafico.setImage(img.getImage());
	}
	/**
	 * Devuelve la cantidad de imagenes.
	 * @return Cantidad de imagenes de la celda.
	 */
	public int getCantElementos() {
		return imagenes.length;
	}

	/**
	 * Devuelve el valor de la celda.
	 * @return El valor de la celda.
	 */
	public Integer getValor() {
		return this.valor;
	}
	/**
	 * Setea el valor en base a la cantidad de imagenes de la Celda.
	 * @param valor Valor a setear.
	 */
	public void setValor(Integer valor) {
		if (valor!=null && valor < this.getCantElementos()) {
			this.valor = valor;
			if (valor < this.imagenes.length) {
				ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[valor]));
				this.grafico.setImage(imageIcon.getImage());
			}
		}else {
			this.valor = null;
		}
	}
	/**
	 * Setea la fila de la Celda.
	 * @param f Fila a setear.
	 */
	public void setFila(int f) {
		fila=f;
	}
	/**
	 * Setea la columna de la Celda.
	 * @param f Columna a setear.
	 */
	public void setCol(int c) {
		col=c;
	}
	/**
	 * Devuelve la fila de la Celda.
	 * @return Entero perteneciente a la fila de la celda.
	 */
	public int getFila() {
		return fila;
	}
	/**
	 * Devuelve la fila de la Celda.
	 * @return Entero perteneciente a la fila de la celda.
	 */
	public int getCol() {
		return col;
	}
	/**
	 * Devuelve la imagen de la Celda.
	 * @return ImageIcon Imagen de la celda.
	 */
	public ImageIcon getGrafico() {
		return this.grafico;
	}

	public void setGrafico(ImageIcon grafico) {
		this.grafico = grafico;
	}
	/**
	 * Devuelve las imagenes de la Celda.
	 * @return String Devuelve las imagenes de la celda.
	 */
	public String[] getImagenes() {
		return this.imagenes;
	}
	/**
	 * Setea las imagenes de la Celda.
	 * @param imagenes Imagenes a setear.
	 */
	public void setImagenes(String[] imagenes) {
		this.imagenes = imagenes;
	}
	/**
	 * Devuelve si la Celda es válida.
	 * @return Verdadero si la Celda es válida,Falso caso contrario.
	 */
	public boolean isEsValida() {
		return esValida;
	}

	/**
	 * Setea si la Celda es válida.
	 */
	public void setEsValida(boolean esValida) {
		this.esValida = esValida;
	}

}
