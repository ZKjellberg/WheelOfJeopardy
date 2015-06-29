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

        ArrayList<FortuneItem> dis = new ArrayList<>();
        /*
        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_always_landscape_portrait)));
        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_add)));
        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_agenda)));
        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_camera)));
        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_compass)));
        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_help)));
        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_mapmode)));
        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_save)));
        */

        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_always_landscape_portrait), FortuneItem.HingeType.Fixed));
        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_add), FortuneItem.HingeType.Fixed));
        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_agenda), FortuneItem.HingeType.Fixed));
        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_camera), FortuneItem.HingeType.Fixed));
        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_compass), FortuneItem.HingeType.Fixed));
        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_help), FortuneItem.HingeType.Fixed));
        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_mapmode), FortuneItem.HingeType.Fixed));
        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_save), FortuneItem.HingeType.Fixed));

//        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_0)));
//        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_1)));
//        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_2)));
//        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_3)));
//        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_4)));
//        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_5)));
//        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_6)));
//        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_7)));
//        dis.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_8)));

        /* These do not work
        dis.add(new FortuneItem(Color.BLACK, 1));
        dis.add(new FortuneItem(Color.BLUE, 1));
        dis.add(new FortuneItem(Color.RED, 1));
        dis.add(new FortuneItem(Color.GREEN, 1));
        dis.add(new FortuneItem(Color.MAGENTA, 1));
        */

        fortuneView.addFortuneItems(dis);

        v.findViewById(R.id.btRandom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random ran = new Random();
                int randomInt = ran.nextInt(fortuneView.getTotalItems());
                //fortuneView.setSelectedItem((fortuneView.getSelectedIndex() == fortuneView.getTotalItems() - 1 ? 0 : fortuneView.getSelectedIndex() + 1));
                //fortuneView.setSelectedItem((fortuneView.getSelectedIndex() == 0 ? fortuneView.getTotalItems() - 1 : fortuneView.getSelectedIndex() - 1));
                Toast.makeText(getActivity(),""+randomInt,Toast.LENGTH_SHORT).show();
                fortuneView.setSelectedItem(randomInt);
            }
        });

        return v;
    }


}
