package mobicent.com.wheelofjeopardy;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.myriadmobile.fortune.FortuneItem;
import com.myriadmobile.fortune.FortuneView;
import com.myriadmobile.fortune.R;

import java.util.ArrayList;
import java.util.Random;


public class WheelFragment extends Fragment {

    FortuneView fortuneView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(mobicent.com.wheelofjeopardy.R.layout.fragment_wheel, container, false);

        fortuneView = (FortuneView) v.findViewById(R.id.dialView);

        ArrayList<FortuneItem> sectors = new ArrayList<>();
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_always_landscape_portrait)));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_add)));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_agenda)));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_camera)));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_compass)));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_help)));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_mapmode)));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_save)));

        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_always_landscape_portrait), FortuneItem.HingeType.Fixed));
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_add), FortuneItem.HingeType.Fixed));
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_agenda), FortuneItem.HingeType.Fixed));
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_camera), FortuneItem.HingeType.Fixed));
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_compass), FortuneItem.HingeType.Fixed));
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_help), FortuneItem.HingeType.Fixed));
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_mapmode), FortuneItem.HingeType.Fixed));
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_save), FortuneItem.HingeType.Fixed));

//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_0)));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_1)));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_2)));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_3)));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_4)));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_5)));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_6)));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_7)));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_8)));

        // These do not work
//        sectors.add(new FortuneItem(Color.BLACK, 1));
//        sectors.add(new FortuneItem(Color.BLUE, 1));
//        sectors.add(new FortuneItem(Color.RED, 1));
//        sectors.add(new FortuneItem(Color.GREEN, 1));
//        sectors.add(new FortuneItem(Color.MAGENTA, 1));

        fortuneView.addFortuneItems(sectors);

        v.findViewById(R.id.btRandom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random ran = new Random();
                int randomInt = ran.nextInt(fortuneView.getTotalItems());
                Toast.makeText(getActivity(),""+randomInt,Toast.LENGTH_SHORT).show();
                fortuneView.setSelectedItem(randomInt);
            }
        });

        return v;
    }


}
