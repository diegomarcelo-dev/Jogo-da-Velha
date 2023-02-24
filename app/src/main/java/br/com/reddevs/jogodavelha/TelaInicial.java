package br.com.reddevs.jogodavelha;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TelaInicial extends Activity {

    TextView texto;
    private Button iniciar;
    private Button q01, q02, q03, q04, q05, q06, q07, q08, q09;

    private final String BUTTON = "button";
    private final String PLAY01 = "O";
    private final String PLAY02 = "X";
    private String ultimoPlay = "X";
    private View view;

    int[][] posicaoFinal = new int[][]{
            {1, 2, 3}, //horizontal
            {4, 5, 6}, //horizontal
            {7, 8, 9}, //horizontal

            {1, 4, 7}, //vertical
            {2, 5, 8}, //vertical
            {3, 6, 9}, //vertical

            {1, 5, 9}, //diagonal
            {3, 5, 7}, //diagonal
    };
    private Object Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(getLayoutInflater().inflate(R.layout.activity_tela_inicial, null));
        //setContentView(R.layout.activity_tela_inicial);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(getView());

        //Foi definido a tag para chamar cada button, estou utilizando por ids para modificar a fonte do texto.
        q01 = (Button) findViewById(R.id.q01);
        q02 = (Button) findViewById(R.id.q02);
        q03 = (Button) findViewById(R.id.q03);
        q04 = (Button) findViewById(R.id.q04);
        q05 = (Button) findViewById(R.id.q05);
        q06 = (Button) findViewById(R.id.q06);
        q07 = (Button) findViewById(R.id.q07);
        q08 = (Button) findViewById(R.id.q08);
        q09 = (Button) findViewById(R.id.q09);

        String montserratFont = "fonts/montserrat.otf";
        Typeface enFont = Typeface.createFromAsset(getAssets(), montserratFont);

        String montserratFontBold = "fonts/montserratextrabold.otf";
        Typeface enFontBold = Typeface.createFromAsset(getAssets(), montserratFontBold);

        texto = findViewById(R.id.texto);
        texto.setTypeface(enFont);

        iniciar = findViewById(R.id.btn);
        iniciar.setTypeface(enFont);

        q01.setTypeface(enFontBold);
        q02.setTypeface(enFontBold);
        q03.setTypeface(enFontBold);
        q04.setTypeface(enFontBold);
        q05.setTypeface(enFontBold);
        q06.setTypeface(enFontBold);
        q07.setTypeface(enFontBold);
        q08.setTypeface(enFontBold);
        q09.setTypeface(enFontBold);

    }

    public void clickButton(View v){

        if(!jogoAcabou()) {
            if (((Button) v).getText().equals("")) {
                if (ultimoPlay.equals(PLAY01)) {
                    ((Button) v).setText(PLAY02);
                    ((Button) v).setTextColor(Color.parseColor("#FF0000"));
                    setLastPlay(PLAY02);
                } else {
                    ((Button) v).setText(PLAY01);
                    ((Button) v).setTextColor(Color.parseColor("#0836DD"));
                    setLastPlay(PLAY01);
                }
            } else {
                Toast.makeText(getView().getContext(), "Ops!Escolha outro quadrado.", Toast.LENGTH_SHORT).show();
            }
            jogoAcabou();
        }
    }

    public Button getButton(int tagNum){
        return (Button)getView().findViewWithTag(BUTTON+tagNum);

    }

    //@SuppressLint("ResourceAsColor")
    public void novoJogo(View v){

        iniciar.setText("REINICIAR");
        setEnableButton(true);
        //setColorBlack();

        for(int i=1; i<=9; ++i){
            if(getButton(i)!=null){
                getButton(i).setText("");
                getButton(i).setBackgroundColor((Color.rgb(78, 30, 163))); //Utilizei RGB pq as outras formas alterava a cor do Button
            }
        }
    }

    public void setEnableButton(boolean b){
        for(int i=1; i<=9; ++i){
            if(getButton(i)!=null){
                getButton(i).setEnabled(b);
            }
        }
    }

    public void setCorTextoButtons(int btn, int colorX){
        getButton(btn).setTextColor(getResources().getColor(colorX));
    }

    public void setCorButtons(int btn, int colorX){
        getButton(btn).setBackgroundColor(getResources().getColor(colorX));
    }

    /*public void setColorBlack(){
        for(int i=0; i<=9; ++i){
            if(getButton(i)!=null){
                setColorButtons(i, R.color.black);
            }
        }

    }*/

    public boolean jogoAcabou() {

        for (int a = 0; a <= 7; ++a) {
            String v1 = getButton(posicaoFinal[a][0]).getText().toString();
            String v2 = getButton(posicaoFinal[a][1]).getText().toString();
            String v3 = getButton(posicaoFinal[a][2]).getText().toString();

            if ((!v1.equals("")) && (!v2.equals("")) && (!v3.equals(""))) {

                if ((v1 == v2) && (v2 == v3)) {
                    setCorTextoButtons(posicaoFinal[a][0], R.color.green);
                    setCorTextoButtons(posicaoFinal[a][1], R.color.green);
                    setCorTextoButtons(posicaoFinal[a][2], R.color.green);
                    setCorButtons(posicaoFinal[a][0], R.color.roxo);
                    setCorButtons(posicaoFinal[a][1], R.color.roxo);
                    setCorButtons(posicaoFinal[a][2], R.color.roxo);
                    Toast.makeText(getView().getContext(), "Fim de Jogo", Toast.LENGTH_SHORT).show();

                    return true;

                }

                else if (v1 != v2 && v2 != v3 && Button != null){
                    Toast.makeText(getView().getContext(), "Jogo Empatado", Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
        }
        return false;
    }

    public View getView(){
        return view;
    }

    public void setView(View view){
        this.view = view;
    }

    public String getLastPlay(){
        return ultimoPlay;
    }

    public void setLastPlay(String lastPlay){
        this.ultimoPlay = lastPlay;
    }
}
