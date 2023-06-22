package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

    private StringBuilder letrasTentadas;

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

        palavras = new ArrayList<>();
        palavras.add("android");
        palavras.add("java");
        palavras.add("kotlin");


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

        ImageButton button = (ImageButton) view;
        String letra = button.getTag().toString().toLowerCase();

        // Definir a letra capturada no EditText
        editLetra.setText(letra);
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
        confirmar = findViewById(R.id.confirmar);
        editLetra = findViewById(R.id.editLetra);
        Letras = findViewById(R.id.Letras);
        textTentativas = findViewById(R.id.tentativa);

        letrasTentadas = new StringBuilder();
        clickLetras();

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String letra = editLetra.getText().toString().toLowerCase();
                if (letra.length() == 1 && letra(letra.charAt(0))) {
                    verificarLetra(letra.charAt(0));
                    editLetra.setText("");
                } else {
                    Toast.makeText(MainActivity2.this, "digite apenas uma letra", Toast.LENGTH_LONG);
                }
            }
        });
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

    private void verificarLetra(char letra) {
        boolean encontrou = false;
        boolean letraDigitada = letrasTentadas.toString().contains(String.valueOf(letra));

        if(letraDigitada){
            Toast.makeText(MainActivity2.this, "Letra já digitada", Toast.LENGTH_LONG).show();
            tentativasRestantes--;
            atualizarTela();
            return;
        }
        for (int i = 0; i < palavraSecreta.length(); i++) {
            if (palavraSecreta.charAt(i) == letra) {
                encontrou = true;
                palavraAdivinhada.setCharAt(i, letra);
            }
        }

        if (!encontrou) {
            Toast.makeText(MainActivity2.this, "Errou", Toast.LENGTH_LONG).show();
            letrasTentadas.append(letra).append(" ");
            textTentativas.setText(letrasTentadas.toString());
            tentativasRestantes--;
        }else {
            letrasTentadas.append(letra).append(" ");

        }
        Letras.setText(palavraAdivinhada.toString());
        textTentativas.setText(letrasTentadas.toString());
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
        letrasTentadas.setLength(0);
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
