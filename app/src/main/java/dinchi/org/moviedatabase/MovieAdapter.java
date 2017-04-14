package dinchi.org.moviedatabase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Srivatsa on 3/13/2017.
 */

public class MovieAdapter extends ArrayAdapter<MovieModel> {

    private ArrayList<MovieModel> movieList;
    Context context;

    public MovieAdapter(Context context, ArrayList<MovieModel> movieList){
        super(context,-1,movieList);
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.movie_list_item, parent, false);

        TextView textviewTitle = (TextView)rowView.findViewById(R.id.txtTitle);
        TextView textviewRating = (TextView)rowView.findViewById(R.id.txtRating);
        TextView textviewGenre = (TextView)rowView.findViewById(R.id.txtGenre);
        TextView textviewYear = (TextView)rowView.findViewById(R.id.txtYear);
        TextView textviewSynop = (TextView)rowView.findViewById(R.id.txtDesc);
        ImageView imageIcon = (ImageView) rowView.findViewById(R.id.imgMovie);

        textviewTitle.setText(movieList.get(position).getTitle());
        textviewRating.setText(movieList.get(position).getRating());
        textviewGenre.setText(movieList.get(position).getGenre());
        textviewYear.setText(movieList.get(position).getYear());
        textviewSynop.setText(movieList.get(position).getSynopsis());

        Log.i("MovieAdapter","https://image.tmdb.org/t/p/w300_and_h450_bestv2" + movieList.get(position).getImage());

        //Render image using Picasso library
        if (!TextUtils.isEmpty(movieList.get(position).getImage())) {
            Picasso.with(context).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" +
                    movieList.get(position).getImage())
//                    .resize(300,150)
//                    .error(R.drawable.ic_plus)
//                    .placeholder(R.drawable.ic_plus)
                    .into(imageIcon);
        }

        return rowView;
    }

    @Override
    public int getCount() {
        return (null != movieList ? movieList.size() : 0);
    }

}

//
//public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.CustomViewHolder>{
//
//    private ArrayList<MovieModel> movieList;
//    Context context;
//
//    public MovieAdapter(Context context, ArrayList<MovieModel> movieList){
//        this.movieList = movieList;
//        this.context = context;
//    }
//
//    @Override
//    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,parent,false);
//        CustomViewHolder cvh = new CustomViewHolder(view);
//        return cvh;
//    }
//
//    @Override
//    public void onBindViewHolder(CustomViewHolder holder, int position) {
//        TextView textviewTitle = holder.tvTitle;
//        TextView textviewRating = holder.tvRating;
//        TextView textviewGenre = holder.tvGenre;
//        TextView textviewYear = holder.tvYear;
//        TextView textviewSynop = holder.tvSynopsis;
//        ImageView imageIcon = holder.imageMovie;
//
//        textviewTitle.setText(movieList.get(position).getTitle());
//        textviewRating.setText(movieList.get(position).getRating());
//        textviewGenre.setText(movieList.get(position).getGenre());
//        textviewYear.setText(movieList.get(position).getYear());
//        textviewSynop.setText(movieList.get(position).getSynopsis());
//
//        //TODO:
////        imageIcon.setImageResource(movieList.get(position).getImage());
//
//        Log.i("MovieAdapter","https://image.tmdb.org/t/p/w300_and_h450_bestv2" + movieList.get(position).getImage());
//
//        //Render image using Picasso library
//        if (!TextUtils.isEmpty(movieList.get(position).getImage())) {
//            Picasso.with(context).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + movieList.get(position).getImage())
//                    .resize(300,150)
////                    .error(R.drawable.ic_plus)
////                    .placeholder(R.drawable.ic_plus)
//                    .into(holder.imageMovie);
//        }
//    }
//
//    @Override
//    public int getItemCount()
//    {
//        return (null != movieList ? movieList.size() : 0);
//    }
//
//    public static class CustomViewHolder extends RecyclerView.ViewHolder{
//
//            TextView tvTitle,tvRating,tvSynopsis,tvGenre,tvYear;
//            ImageView imageMovie;
//
//        public CustomViewHolder(View itemView) {
//            super(itemView);
//
//            this.tvTitle = (TextView)itemView.findViewById(R.id.txtTitle);
//            this.tvRating = (TextView)itemView.findViewById(R.id.txtRating);
//            this.tvSynopsis = (TextView)itemView.findViewById(R.id.txtDesc);
//            this.tvGenre = (TextView)itemView.findViewById(R.id.txtGenre);
//            this.tvYear = (TextView)itemView.findViewById(R.id.txtYear);
//            this.imageMovie = (ImageView)itemView.findViewById(R.id.imgMovie);
//        }
//    }
//}
