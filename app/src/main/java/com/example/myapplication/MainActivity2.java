package com.example.myapplication;

import com.google.android.material.button.MaterialButton;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private TextView playerInfoTextView;
    private TextView timeTextView;

    private TextView lettersTextView;
    private TextView hangmanTextView;
    private ImageView avatar;
    private TextView textV;

    private TextView Letras;

    private List<String> palavras;

    private String palavraSecreta;
    private StringBuilder palavraAdivinhada;
    private Thread cronometroThread;
    private boolean cronometrorodando;
    private long duracaoCronometro, duracaoInicial;

    private ImageView ImageForca;

    private TextView Tempo;
    private ImageView parte;
    private int tentativasRestantes;

    private TextView editLetra;
    private TextView textTentativas;

    private List<String> letrasDigitadas;

    private Button buA;
    private Button buB;
    private Button buC;
    private Button buD;
    private Button buE;
    private Button buF;
    private Button buG;
    private Button buH;
    private Button buI;
    private Button buJ;
    private Button buK;
    private Button buL;
    private Button buM;
    private Button buN;
    private Button buO;
    private Button buP;
    private Button buQ;
    private Button buR;
    private Button buS;
    private Button buT;
    private Button buU;
    private Button buV;
    private Button buW;
    private Button buX;
    private Button buY;
    private Button buZ, confirmar;



    private String palavraEscolhida;
    private StringBuilder palavraExibida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Letras = findViewById(R.id.Letras);
        letrasDigitadas = new ArrayList<>();
        playerInfoTextView = findViewById(R.id.playerInfoTextView);
        timeTextView = findViewById(R.id.Tempo);
        hangmanTextView = findViewById(R.id.forca);
        avatar = findViewById(R.id.avatarTela);
        textV = findViewById(R.id.textView3);

        // Recupere o nick do jogador dos extras do Intent
        String nick = getIntent().getStringExtra("nick");

        // Atualize o TextView de informações do jogador com o nick
        playerInfoTextView.setText("Informações do Jogador: " + nick);

        int imagem = getIntent().getIntExtra("image", 0);
        avatar.setImageResource(imagem);

        String texto = getIntent().getStringExtra("texto");
        textV.setText(texto);

        palavras = MainActivity.getMeuBancoDeDadosHelper().getPalavras();

        inicializando();
        verificarLetras();
    }

    public void verificarLetras(){
        editLetra.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0 && !letra(charSequence.charAt(0))) {
                    // Remover o texto não válido
                    editLetra.setText("");
                    Toast.makeText(MainActivity2.this, "Digite apenas uma letra válida", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private boolean letra(char c) {
        return Character.isLetter(c);
    }

    @Override
    public void onClick(View view) {

        Button button = (Button) view;
        String letra = button.getText().toString().toLowerCase();

    }

    public void inicializando() {
        buA = findViewById(R.id.buA);
        buB = findViewById(R.id.buB);
        buC = findViewById(R.id.buC);
        buD = findViewById(R.id.buD);
        buE = findViewById(R.id.buE);
        buF = findViewById(R.id.buF);
        buG = findViewById(R.id.buG);
        buH = findViewById(R.id.buH);
        buI = findViewById(R.id.buI);
        buJ = findViewById(R.id.buJ);
        buK = findViewById(R.id.buK);
        buL = findViewById(R.id.buL);
        buM = findViewById(R.id.buM);
        buN = findViewById(R.id.buN);
        buO = findViewById(R.id.buO);
        buP = findViewById(R.id.buP);
        buQ = findViewById(R.id.buQ);
        buR = findViewById(R.id.buR);
        buS = findViewById(R.id.buS);
        buT = findViewById(R.id.buT);
        buU = findViewById(R.id.buU);
        buV = findViewById(R.id.buV);
        buW = findViewById(R.id.buW);
        buX = findViewById(R.id.buX);
        buY = findViewById(R.id.buY);
        buZ = findViewById(R.id.buZ);


        Tempo = findViewById(R.id.Tempo);
        ImageForca = findViewById(R.id.ImageForca);
        confirmar = findViewById(R.id.buconfirmar);
        editLetra = findViewById(R.id.editLetra);
        Letras = findViewById(R.id.Letras);
        textTentativas = findViewById(R.id.tentativa);
        clickLetras();

        Button[] botoes = new Button[]{
                buA,
                buB,
                buC,
                buD,
                buE,
                buF,
                buG,
                buH,
                buI,
                buJ,
                buK,
                buL,
                buM,
                buN,
                buO,
                buP,
                buQ,
                buR,
                buS,
                buT,
                buU,
                buV,
                buW,
                buX,
                buY,
                buZ
        };

        for (Button botao : botoes) {
            botao.setOnClickListener(v -> {
                Button button = (Button)v;
                String letra = button.getText().toString().toLowerCase();
                verificarLetra(letra);
            });
        }

        duracaoInicial = 15;
        duracaoCronometro = duracaoInicial;
        inicalizarJogo();
        iniciarCronometro();
    }

    private void iniciarCronometro() {
        cronometrorodando = true;
        cronometroThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (cronometrorodando) {
                        Thread.sleep(1000);
                        duracaoCronometro--;


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Tempo.setText("Tempo restante:" + duracaoCronometro + "S");

                                if (duracaoCronometro <= 0) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                                    builder.setTitle("Tempo esgotado").setIcon(R.drawable.ic_erro)
                                            .setMessage("Deseja reiniciar o jogo?")
                                            .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    reiniciarJogo();
                                                    dialogInterface.dismiss();
                                                }
                                            }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    onDestroy();
                                                }
                                            });
                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                    pararCronometro();
                                }
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        cronometroThread.start();
    }

    private void pararCronometro() {
        cronometrorodando = false;
        cronometroThread.interrupt();
    }

    public void clickLetras() {
        buA.setOnClickListener(this);
        buB.setOnClickListener(this);
        buC.setOnClickListener(this);
        buD.setOnClickListener(this);
        buE.setOnClickListener(this);
        buF.setOnClickListener(this);
        buG.setOnClickListener(this);
        buH.setOnClickListener(this);
        buI.setOnClickListener(this);
        buJ.setOnClickListener(this);
        buK.setOnClickListener(this);
        buL.setOnClickListener(this);
        buM.setOnClickListener(this);
        buN.setOnClickListener(this);
        buO.setOnClickListener(this);
        buP.setOnClickListener(this);
        buQ.setOnClickListener(this);
        buR.setOnClickListener(this);
        buS.setOnClickListener(this);
        buT.setOnClickListener(this);
        buU.setOnClickListener(this);
        buV.setOnClickListener(this);
        buW.setOnClickListener(this);
        buX.setOnClickListener(this);
        buY.setOnClickListener(this);
        buZ.setOnClickListener(this);
    }

    private void inicalizarJogo() {
        Random random = new Random();
        int index = random.nextInt(palavras.size());
        palavraSecreta = palavras.get(index);
        palavraAdivinhada = new StringBuilder();
        tentativasRestantes = 6;

        for (int i = 0; i < palavraSecreta.length(); i++) {
            palavraAdivinhada.append("_");
        }
        atualizarTela();
    }

    private String construirPalavra() {
        StringBuilder palavra = new StringBuilder();
        for (int i = 0; i < palavraSecreta.length(); i++) {
            String letra = String.valueOf(palavraSecreta.charAt(i));
            if (letrasDigitadas.contains(letra)) {
                palavra.append(letra);
            } else {
                palavra.append("_");
            }
        }
        Log.d("palavra", palavra.toString());
        return palavra.toString();
    }

    private void construirAtualizarPalavra() {
        TextView tentativa = findViewById(R.id.Letras);
        tentativa.setText(construirPalavra());
    }

    private void verificarVitoria() {
        if (!construirPalavra().contains("_")) {
            // TODO: Venceu
        }
    }

    private void verificarLetra(String letra) {
        if (letrasDigitadas.contains(String.valueOf(letra))) {
            Toast.makeText(this, "Letra já digitada", Toast.LENGTH_LONG).show();
            return;
        }
        letrasDigitadas.add(letra);

        if (!palavraSecreta.toLowerCase().contains(letra.toLowerCase())) {
            tentativasRestantes--;
            Toast.makeText(this, "Letra errada", Toast.LENGTH_LONG).show();
        } else {
            construirAtualizarPalavra();
            verificarVitoria();
            Toast.makeText(this, "Letra correta!", Toast.LENGTH_SHORT).show();
        }
        atualizarTela();
    }


    private void atualizarTela() {
        String palavraExibida = palavraAdivinhada.toString();
        Letras.setText(palavraExibida);

        if (palavraExibida.equals(palavraSecreta)) {
            Toast.makeText(this, "Parabens", Toast.LENGTH_LONG).show();
            reiniciarJogo();
        } else if (tentativasRestantes == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
            builder.setTitle("Você perdeu").setIcon(R.drawable.ic_erro)
                    .setMessage("Deseja reiniciar o jogo?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            reiniciarJogo();
                            dialogInterface.dismiss();
                        }
                    }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            pararCronometro();
                            onDestroy();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();

        } else {
            int imagemForcaResouce = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                imagemForcaResouce = getResources().getIdentifier("forca_" + tentativasRestantes, "drawable", getOpPackageName());
            }
            ImageForca.setImageResource(imagemForcaResouce);
        }
    }

    private void reiniciarJogo() {
        letrasDigitadas.clear();
        textTentativas.setText("");
        duracaoCronometro = duracaoInicial;
        inicalizarJogo();
        pararCronometro();
        iniciarCronometro();
    }

    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
