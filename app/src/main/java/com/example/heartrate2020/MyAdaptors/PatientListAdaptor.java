package com.example.heartrate2020.MyAdaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heartrate2020.CustomItemClickListener;
import com.example.heartrate2020.Model.Pationt;
import com.example.heartrate2020.R;

import java.util.List;



/*

public class PationtListAdaptor extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;

    private List <Pationt> pationtItems ;

    public PationtListAdaptor(Activity activity, List<Pationt> pationtItems) {
        this.activity = activity;
        this.pationtItems = pationtItems;
    }

    @Override
    public int getCount() {
        return pationtItems.size();
    }

    @Override
    public Object getItem(int i) {
        return pationtItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (inflater == null )
        {
         inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view==null)
            view=inflater.inflate(R.layout.custome, null);

        TextView Pationt_Name = view.findViewById(R.id.PEName);
        TextView Pationt_ID = view.findViewById(R.id.PaID);

        final Pationt item = pationtItems.get(i);

        Pationt_Name.setText(item.getPation_Name());
        Pationt_ID.setText(String.valueOf(item.getPationt_ID()));


        return view;
    }
}
*/



    public class PatientListAdaptor  extends RecyclerView.Adapter <PatientListAdaptor.ViewHolder>
    {

        private  List <Pationt> pationtList;

        Context context;
        CustomItemClickListener listener;

        public PatientListAdaptor(Context context, List<Pationt> PatientList , CustomItemClickListener listener )
        {
            this.context = context;
            this.pationtList = PatientList;
            this.listener = listener;
        }

        @Override
        public ViewHolder  onCreateViewHolder(ViewGroup viewGroup, int i)
        {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.patient_row, viewGroup, false);
            return new PatientListAdaptor.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(PatientListAdaptor.ViewHolder viewHolder, final int i)
        {
            viewHolder.textView.setText(pationtList.get(i).getPation_Name());
            viewHolder.bind(pationtList.get(i) , listener);
        }

        @Override
        public int getItemCount() {
            return pationtList.size();
        }

        public class ViewHolder extends  RecyclerView.ViewHolder
        {
            TextView textView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textView= itemView.findViewById(R.id.ppname);
            }

            public void bind(final Pationt Patient, final CustomItemClickListener listener) {

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        listener.OnItemClick(Patient);
                    }
                });
            }



        }



    }
