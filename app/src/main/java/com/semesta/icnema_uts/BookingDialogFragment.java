package com.semesta.icnema_uts;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BookingDialogFragment extends DialogFragment {

    private GridView gridViewSeats;
    private Spinner spinnerTime;
    private MaterialButton buttonConfirm;
    private TextView textPrice;

    private String selectedTime = "";
    private List<String> selectedSeats = new ArrayList<>();
    private static final int PRICE_PER_SEAT = 50000; // Harga per kursi

    public static BookingDialogFragment newInstance() {
        return new BookingDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_booking, container, false);

        // Initialize UI components
        gridViewSeats = view.findViewById(R.id.gridview_seats);
        spinnerTime = view.findViewById(R.id.spinner_showtimes);
        buttonConfirm = view.findViewById(R.id.btn_confirm_booking);
        textPrice = view.findViewById(R.id.text_price);

        setupSpinner();
        setupGridView();
        updatePrice();

        buttonConfirm.setOnClickListener(v -> {
            if (selectedSeats.isEmpty() || selectedTime.isEmpty()) {
                Toast.makeText(getContext(), "Pilih waktu dan kursi terlebih dahulu!", Toast.LENGTH_SHORT).show();
            } else {
                // Show success message
                Toast.makeText(getContext(), "Pesanan telah berhasil dibuat!", Toast.LENGTH_SHORT).show();
                // Generate QR Code or proceed with booking
                generateQRCode();
            }
        });

        return view;
    }

    private void setupSpinner() {
        String[] times = {"10:00 AM", "01:00 PM", "04:00 PM", "07:00 PM"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, times);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTime.setAdapter(adapter);

        spinnerTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTime = times[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void setupGridView() {
        int rows = 5; // Number of rows
        int columns = 4; // Number of columns
        List<String> seats = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            char rowLabel = (char) ('A' + i);
            for (int j = 1; j <= columns; j++) {
                seats.add(rowLabel + String.valueOf(j));
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, seats);
        gridViewSeats.setAdapter(adapter);

        gridViewSeats.setOnItemClickListener((parent, view, position, id) -> {
            String seat = seats.get(position);
            if (selectedSeats.contains(seat)) {
                selectedSeats.remove(seat);
                view.setBackgroundColor(Color.TRANSPARENT);  // Deselect seat
            } else {
                selectedSeats.add(seat);
                view.setBackgroundColor(Color.LTGRAY);  // Select seat
            }
            updatePrice();
        });
    }

    private void updatePrice() {
        int totalPrice = selectedSeats.size() * PRICE_PER_SEAT;
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.GERMANY);
        textPrice.setText("Harga: Rp. " + numberFormat.format(totalPrice));
    }

    private void generateQRCode() {
        // Generate QR Code or proceed with booking logic
        String bookingInfo = "Waktu: " + selectedTime + "\nKursi: " + selectedSeats.toString();
        QRCodeDialogFragment qrCodeDialog = QRCodeDialogFragment.newInstance(bookingInfo);
        qrCodeDialog.show(getParentFragmentManager(), "QRCodeDialog");
    }

    @Override
    public void onStart() {
        super.onStart();
        // Set dialog size to match almost the full screen
        if (getDialog() != null) {
            getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }
}