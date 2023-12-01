package com.example.controlenamao.masks;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.Locale;

public class MaskedRenamed {

        // Define uma máscara para a data (DD/MM/AAAA)
        private static final String DATE_MASK = "###-####";

        public static void addRenamedMask(final EditText editText) {
            editText.addTextChangedListener(new TextWatcher() {
                private boolean isUpdating = false;
                private String oldText = "";

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String newText = s.toString();
                    if (isUpdating) {
                        oldText = newText;
                        isUpdating = false;
                        return;
                    }

                    if (!newText.equals(oldText)) {
                        String digitsOnly = newText.replaceAll("[^A-Z 0-9]".toUpperCase(Locale.ROOT), "");
                        StringBuilder formatted = new StringBuilder();

                        int index = 0;
                        for (char m : DATE_MASK.toCharArray()) {
                            if (m != '#') {
                                formatted.append(m);
                                continue;
                            }
                            if (index < digitsOnly.length()) {
                                formatted.append(digitsOnly.charAt(index));
                                index++;
                            }
                        }

                        isUpdating = true;
                        editText.setText(formatted.toString());
                        editText.setSelection(formatted.length());
                    }
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // Não é necessário implementar esta parte
                }

                @Override
                public void afterTextChanged(Editable s) {
                    // Não é necessário implementar esta parte
                }
            });
        }
}