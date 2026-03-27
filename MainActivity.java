public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Chamar o Dialog de Login assim que a tela abre
        showLoginDialog();

        // Configurar botões da Tela Principal
        findViewById(R.id.btnConfig).setOnClickListener(v -> {
            startActivity(new Intent(this, ConfigActivity.class));
        });

        findViewById(R.id.btnExit).setOnClickListener(v -> finishAffinity());
    }

    private void showLoginDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_login, null);
        builder.setView(view);
        builder.setCancelable(false); // Impede fechar clicando fora

        AlertDialog dialog = builder.create();
        
        EditText etUser = view.findViewById(R.id.etLoginUser);
        EditText etPass = view.findViewById(R.id.etLoginPass);

        view.findViewById(R.id.btnEntrar).setOnClickListener(v -> {
            SharedPreferences pref = getSharedPreferences("AppPrefs", MODE_PRIVATE);
            // Se for a 1ª vez, as Strings retornam "" por padrão
            String savedUser = pref.getString("user", "");
            String savedPass = pref.getString("pass", "");

            if (etUser.getText().toString().equals(savedUser) && 
                etPass.getText().toString().equals(savedPass)) {
                dialog.dismiss(); // Sucesso
            } else {
                Toast.makeText(this, "Credenciais Inválidas!", Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.btnSairApp).setOnClickListener(v -> finishAffinity());

        dialog.show();
    }
}
