public class ConfigActivity extends AppCompatActivity {

    private EditText etNewUser, etNewPass1, etNewPass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        etNewUser = findViewById(R.id.etNewUser);
        etNewPass1 = findViewById(R.id.etNewPass1);
        etNewPass2 = findViewById(R.id.etNewPass2);

        findViewById(R.id.btnConfirm).setOnClickListener(v -> saveSettings());
        findViewById(R.id.btnCancel).setOnClickListener(v -> finish());
    }

    private void saveSettings() {
        String user = etNewUser.getText().toString().trim();
        String p1 = etNewPass1.getText().toString();
        String p2 = etNewPass2.getText().toString();

        // Regras de Validação
        if (user.isEmpty()) {
            Toast.makeText(this, "Usuário não pode ser vazio!", Toast.LENGTH_SHORT).show();
        } else if (!p1.equals(p2)) {
            Toast.makeText(this, "As senhas não coincidem!", Toast.LENGTH_SHORT).show();
        } else if (p1.length() < 8) {
            Toast.makeText(this, "Senha deve ter 8+ caracteres!", Toast.LENGTH_SHORT).show();
        } else {
            // Persistência com SharedPreferences
            SharedPreferences pref = getSharedPreferences("AppPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("user", user);
            editor.putString("pass", p1);
            editor.apply();

            Toast.makeText(this, "Configurações Salvas!", Toast.LENGTH_SHORT).show();
            finish(); // Volta para a MainActivity
        }
    }
}
