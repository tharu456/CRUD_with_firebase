package com.example.crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EditUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        FirebaseFirestore db=FirebaseFirestore.getInstance();

        TextInputEditText firstname=findViewById(R.id.firstNameET);
        TextInputEditText lastname=findViewById(R.id.lastNameET);
        TextInputEditText phone=findViewById(R.id.phoneNumberET);
        TextInputEditText bio=findViewById(R.id.bioET);
        MaterialButton save=findViewById(R.id.save);
        MaterialButton delete=findViewById(R.id.delete);

        firstname.setText(App.user.getFirstname());
        lastname.setText(App.user.getLastname());
        phone.setText(App.user.getPhone());
        bio.setText(App.user.getBio());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("users").document(App.user.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(EditUserActivity.this, "user deleted successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditUserActivity.this, "failed to delete user", Toast.LENGTH_SHORT).show();
                    }
                });
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> user=new HashMap<>();
                        user.put("firstname", Objects.requireNonNull(firstname.getText()).toString());
                        user.put("lastname", Objects.requireNonNull(lastname.getText()).toString());
                        user.put("phone", Objects.requireNonNull(phone.getText()).toString());
                        user.put("bio", Objects.requireNonNull(bio.getText()).toString());

                        db.collection("users").document(App.user.getId()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(EditUserActivity.this, "saved successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(EditUserActivity.this, "failed to save changes", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
    }
}