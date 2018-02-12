package util;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Util {
	
	/**
	 * Monta a mascara para os campos CNPJ.
	 *
	 * @param textField TextField
	 */
	public static void isbnField(final TextField textField) {
	    maxField(textField, 15);

	    textField.lengthProperty().addListener(new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
	            String value = textField.getText();
	            value = value.replaceAll("[^0-9]", "");
	            value = value.replaceFirst("(\\d{2})(\\d)", "$1.$2");
	            value = value.replaceFirst("(\\d{2})\\.(\\d{3})(\\d)", "$1.$2.$3");
	            value = value.replaceFirst("\\.(\\d{3})(\\d)", ".$1/$2");
	            value = value.replaceFirst("(\\d{4})(\\d)", "$1-$2");
	            textField.setText(value);
	            positionCaret(textField);
	        }
	    });
	}
	
	/**
	 * Monta a mascara para Moeda.
	 *
	 * @param textField TextField
	 */
	public static void monetaryField(final TextField textField) {
	    textField.setAlignment(Pos.CENTER_RIGHT);
	    textField.lengthProperty().addListener(new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	            String value = textField.getText();
	            value = value.replaceAll("[^0-9]", "");
	            value = value.replaceAll("([0-9]{1})([0-9]{14})$", "$1.$2");
	            value = value.replaceAll("([0-9]{1})([0-9]{11})$", "$1.$2");
	            value = value.replaceAll("([0-9]{1})([0-9]{8})$", "$1.$2");
	            value = value.replaceAll("([0-9]{1})([0-9]{5})$", "$1.$2");
	            value = value.replaceAll("([0-9]{1})([0-9]{2})$", "$1,$2");
	            textField.setText(value);
	            positionCaret(textField);

	            textField.textProperty().addListener(new ChangeListener<String>() {
	                @Override
	                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
	                    if (newValue.length() > 9)
	                        textField.setText(oldValue);
	                }
	            });
	        }
	    });
	}
	
	/**
	 * Retorna monetário formatado para o banco MySQL
	 */
	public static Double formatDouble(String valor) {	
		String num = "";
		if(num != null) {
			num = valor.replace(".", "");
			num = num.replaceAll(",", ".");	
		}
		return Double.parseDouble(num);
	}
	    
		/**
		 * Formata o campo de texto para no máximo 250 caracteres.
		 *
		 * @param textArea TextArea
		 */
		public static void textAreaField(final TextArea textArea) {
			textArea.lengthProperty().addListener(new ChangeListener<Number>() {
		        @Override
		        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		            textArea.textProperty().addListener(new ChangeListener<String>() {
		                @Override
		                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
		                    if (newValue.length() > 250)
		                    	textArea.setText(oldValue);
		                }
		            });
		        }
		    });
	}
	
	/**
	 * @param textField TextField.
	 * @param length    Tamanho do campo.
	 */
	private static void maxField(final TextField textField, final Integer length) {
	    textField.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
	            if (newValue.length() > length)
	                textField.setText(oldValue);
	        }
	    });
	}
	
	/**
	 * Devido ao incremento dos caracteres das mascaras eh necessario que o cursor sempre se posicione no final da string.
	 *
	 * @param textField TextField
	 */
	private static void positionCaret(final TextField textField) {
	    Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	            // Posiciona o cursor sempre a direita.
	            textField.positionCaret(textField.getText().length());
	        }
	    });
	}
}
