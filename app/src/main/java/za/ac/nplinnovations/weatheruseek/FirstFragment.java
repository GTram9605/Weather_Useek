package za.ac.nplinnovations.weatheruseek;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.squareup.picasso.Picasso;

import za.ac.nplinnovations.weatheruseek.databinding.FragmentFirstBinding;
import za.ac.nplinnovations.weatheruseek.pojos.Constants;
import za.ac.nplinnovations.weatheruseek.pojos.api.responses.ResponseWeather;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private ResponseWeather mData;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        if(getArguments() != null){
            Bundle b = getArguments().getBundle(null);

            mData = (ResponseWeather)
                    b.getSerializable(Constants.PASSED_DATA);
        }

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        if (mData != null){
            binding.tvDescription.setText(mData.getWeather().getDescription());
            binding.tvLocation.setText(mData.getName() + ", " + mData.getSys().getCountry());
            binding.tvTimeRequested.setText(mData.getDt());
            binding.tvTemperature.setText(mData.getMain().getTemp());
            Picasso.get().load(R.drawable.cloudy).into(binding.ivWeatherIcon);
            binding.tvMinTemp.setText(mData.getMain().getTemp_min());
            binding.tvMaxTemp.setText(mData.getMain().getTemp_max());
            binding.tvTempFeelsLike.setText(mData.getMain().getFeels_like());
            binding.tvHumidity.setText(mData.getMain().getHumidity());
            binding.tvAirPressure.setText(mData.getMain().getPressure());
            binding.tvWindSpeed.setText("" + mData.getWind().getSpeed());
        }else{
            binding.tvDescription.setText("");
            binding.tvLocation.setText("");
            binding.tvTimeRequested.setText("");
            binding.tvTemperature.setText("");
            Picasso.get().load(R.drawable.cloudy).into(binding.ivWeatherIcon);
            binding.tvMinTemp.setText("");
            binding.tvMaxTemp.setText("");
            binding.tvTempFeelsLike.setText("");
            binding.tvHumidity.setText("");
            binding.tvAirPressure.setText("");
            binding.tvWindSpeed.setText("");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}