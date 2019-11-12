package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$");
    private FirebaseAuth firebaseAuth;
    private EditText editTextEmail;
    private EditText editTextPassword;
    Food_Ranking food_ranking = new Food_Ranking();
    Food_Ranking hating_food = new Food_Ranking();
    CheckBox checkbox1, checkbox2, checkbox3, checkbox4, checkbox5, checkbox6, checkbox7;
    CheckBox checkbox8, checkbox9, checkbox10;
    RadioButton radioButton;
    private EditText editNickName;
    //

    private String email = "";
    private String password = "";
    Calendar myCalendar = Calendar.getInstance();
    private Spinner spinner2;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    private boolean isValidEmail() {
        if (email.isEmpty()) {
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            return false;
        } else {
            return true;
        }
    }

    private boolean isValidPasswd() {
        if (password.isEmpty()) {
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            return false;
        } else {
            return true;
        }
    }

    private void createUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, R.string.success_signup, Toast.LENGTH_SHORT).show();
                            String uid = task.getResult().getUser().getUid();
                            UserModel userModel = new UserModel();
                            userModel.UserEmail = editTextEmail.getText().toString();
                            //userModel.UserID = uid;
                            userModel.UserNickName = editNickName.getText().toString();
                            //userModel.UserHate = spinner2.getTextAlignment();
                            FirebaseDatabase.getInstance().getReference().child("User").child(uid).setValue(userModel);
                            FirebaseDatabase.getInstance().getReference().child("User").child(uid).child("Food_Rank").setValue(food_ranking);
                            FirebaseDatabase.getInstance().getReference().child("User").child(uid).child("today_hate_food").setValue(hating_food);
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, R.string.failed_signup, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void singUp(View view) {
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();
        if(checkbox1.isChecked())
        {
            food_ranking.Korean = -1000;
        }
        if(checkbox2.isChecked())
        {
            food_ranking.Snack = -1000;
        }
        if(checkbox3.isChecked())
        {
            food_ranking.asian = -1000;
        }
        if(checkbox4.isChecked())
        {
            food_ranking.chicken = -1000;
        }
        if(checkbox5.isChecked())
        {
            food_ranking.pizza = -1000;
        }
        if(checkbox6.isChecked())
        {
            food_ranking.soup = -1000;
        }
        if(checkbox7.isChecked())
        {
            food_ranking.lunch_box = -1000;
        }
        if(checkbox8.isChecked())
        {
            food_ranking.fast_food = -1000;
        }
        if(checkbox9.isChecked())
        {
            food_ranking.dessert = -1000;
        }
        if(checkbox10.isChecked())
        {
            food_ranking.china = -1000;
        }
        if(isValidEmail() && isValidPasswd()) {
            createUser(email, password);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.et_eamil);
        editTextPassword = findViewById(R.id.et_password);
        editNickName = findViewById(R.id.et_nickname);
        editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        EditText et_Date = (EditText) findViewById(R.id.et_birth);
        et_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegisterActivity.this, AlertDialog.THEME_HOLO_DARK,myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                //new DatePickerDialog(RegisterActivity.this, myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        checkbox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkbox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkbox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkbox4 = (CheckBox) findViewById(R.id.checkBox4);
        checkbox5 = (CheckBox) findViewById(R.id.checkBox5);
        checkbox6 = (CheckBox) findViewById(R.id.checkBox6);
        checkbox7 = (CheckBox) findViewById(R.id.checkBox7);
        checkbox8 = (CheckBox) findViewById(R.id.checkBox8);
        checkbox9 = (CheckBox) findViewById(R.id.checkBox9);
        checkbox10 = (CheckBox) findViewById(R.id.checkBox10);
        radioButton = (RadioButton) findViewById(R.id.radioButton);

        checkbox1.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton.setChecked(false);
            }
        });
        checkbox2.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton.setChecked(false);
            }
        });
        checkbox3.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton.setChecked(false);
            }
        });
        checkbox4.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton.setChecked(false);
            }
        });
        checkbox5.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton.setChecked(false);
            }
        });
        checkbox6.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton.setChecked(false);
            }
        });
        checkbox7.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton.setChecked(false);
            }
        });
        checkbox8.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton.setChecked(false);
            }
        });
        checkbox9.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton.setChecked(false);
            }
        });
        checkbox10.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton.setChecked(false);
            }
        });

        radioButton.setOnClickListener(new RadioButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox1.setChecked(false);
                checkbox2.setChecked(false);
                checkbox3.setChecked(false);
                checkbox4.setChecked(false);
                checkbox5.setChecked(false);
                checkbox6.setChecked(false);
                checkbox7.setChecked(false);
                checkbox8.setChecked(false);
                checkbox9.setChecked(false);
                checkbox10.setChecked(false);
            }
        });

    }

    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    private void updateLabel() {
        String myFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        EditText et_date = (EditText) findViewById(R.id.et_birth);
        et_date.setText(sdf.format(myCalendar.getTime()));
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Toast.makeText(getApplicationContext(), year + " + monthOfYear + " + dayOfMonth , Toast.LENGTH_SHORT).show();
        }
    };
}
