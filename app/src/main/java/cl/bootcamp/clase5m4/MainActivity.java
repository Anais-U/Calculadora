package cl.bootcamp.clase5m4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText num1, num2;
    Button btnSumar, btnRestar, btnMultiplicar, btnDividir;
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enlazar las vistas con los elementos del layout
        num1 = findViewById(R.id.etnum1);
        num2 = findViewById(R.id.etnum2);
        btnSumar = findViewById(R.id.btnSumar);
        btnRestar = findViewById(R.id.btnRestar);
        btnMultiplicar = findViewById(R.id.btnMultiplicar);
        btnDividir = findViewById(R.id.btnDividir);
        tvResultado = findViewById(R.id.tvResultado);

        // Configurar los eventos click de los botones
        btnSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarOperacion("+");
            }
        });

        btnRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarOperacion("-");
            }
        });

        btnMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarOperacion("*");
            }
        });

        btnDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarOperacion("/");
            }
        });
    }

    // Método para realizar la operación de suma, resta, multiplicación o división
    private void realizarOperacion(String operacion) {
        // Obtener los valores de los EditText
        String num1String = num1.getText().toString();
        String num2String = num2.getText().toString();

        // Verificar si los campos no están vacíos
        if (!num1String.isEmpty() && !num2String.isEmpty()) {
            try {
                // Convertir los valores a enteros
                int numero1 = Integer.parseInt(num1String);
                int numero2 = Integer.parseInt(num2String);
                int resultadoInt = 0;
                double resultadoDouble;

                // Realizar la operación dependiendo del botón que se presione
                switch (operacion) {
                    case "+":
                        resultadoInt = numero1 + numero2;
                        tvResultado.setText("Resultado: " + resultadoInt);
                        break;
                    case "-":
                        resultadoInt = numero1 - numero2;
                        tvResultado.setText("Resultado: " + resultadoInt);
                        break;
                    case "*":
                        resultadoInt = numero1 * numero2;
                        tvResultado.setText("Resultado: " + resultadoInt);
                        break;
                    case "/":
                        if (numero2 != 0) {
                            // Asegurar que no haya división por cero
                            resultadoDouble = (double) numero1 / numero2;
                            if (resultadoDouble == (int) resultadoDouble) {
                                // Si el resultado es un número entero exacto
                                tvResultado.setText("Resultado: " + (int) resultadoDouble);
                            } else {
                                // Si el resultado tiene una parte decimal
                                tvResultado.setText("Resultado: " + resultadoDouble);
                            }
                        } else {
                            tvResultado.setText("Error: División por 0");
                        }
                        break;
                }

            } catch (NumberFormatException e) {
                // Manejar el caso donde la conversión falla (aunque con inputType="number" es poco probable)
                mostrarError(operacion);
            }
        } else {
            // Manejar el caso en que uno o ambos campos estén vacíos
            mostrarError(operacion);
        }
    }

    // Método para mostrar un mensaje de error en el TextView de resultado
    private void mostrarError(String operacion) {
        tvResultado.setText("Error en los números");
    }
}

