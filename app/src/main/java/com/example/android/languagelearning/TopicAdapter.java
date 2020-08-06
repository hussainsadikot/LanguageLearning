package com.example.android.languagelearning;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class TopicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Topics> topics;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
//        void onItemClick(int position);

        void onButtonClick(int position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position  == 0) {
            return R.layout.create_account_card;
        } else {
            return R.layout.topic_list;
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public TopicAdapter(Context context, List<Topics> topics) {
        this.context = context;
        this.topics = topics;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        final RecyclerView.ViewHolder holder;
        switch (viewType) {

            case R.layout.topic_list:
                view = LayoutInflater.from(context).inflate(R.layout.topic_list, parent,false);
                holder = new TopicViewHolder(view, mListener);
                break;
            case R.layout.create_account_card:
                view = LayoutInflater.from(context).inflate(R.layout.create_account_card, parent, false);
                holder = new CreateAccountViewHolder(view);
                break;


            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof TopicViewHolder) {
            int TopicPosition =position-1;
            Topics topics1 = topics.get(TopicPosition);

            ((TopicViewHolder) holder).textViewTitle.setText(topics1.getTitle());
            ((TopicViewHolder) holder).textViewProgress.setText(topics1.getProgressText());
            ((TopicViewHolder) holder).progressBar.getProgress();
        } else if (holder instanceof CreateAccountViewHolder) {
//            ((CreateAccountViewHolder) holder).textViewSignIn.setText(context.getResources().getString(R.string.sign_in));


            ((CreateAccountViewHolder) holder).textViewSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Sign in", Toast.LENGTH_SHORT).show();
                    context.startActivity( new Intent(context, LoginActivity.class));
                }
            });
            ((CreateAccountViewHolder) holder).createAccountButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Create Account", Toast.LENGTH_SHORT).show();

                    context.startActivity( new Intent(context, RegisterActivity.class));
                }
            });


        }

    }

    @Override
    public int getItemCount() {
        return topics.size()+1;
    }

    public static class TopicViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewProgress;
        ProgressBar progressBar;
        Button button;


        public TopicViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.title_topic);
            textViewProgress = itemView.findViewById(R.id.progress_tv);
            progressBar = itemView.findViewById(R.id.progressbar_topic);
            textViewTitle = itemView.findViewById(R.id.title_topic);
            button = itemView.findViewById(R.id.topic_button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onButtonClick(position);

                        }

                    }

                }
            });
        }
//        public void bindListWithPosition(int position){
//            textViewTitle.setText((Topics.getTitle()).toString());
//            textViewProgress.setText((Topics.getProgressText()).toString());
//        }
    }

    class CreateAccountViewHolder extends RecyclerView.ViewHolder {
        public TextView textMessage, textView, textViewSignIn;
        public Button createAccountButton;


        public CreateAccountViewHolder(@NonNull View itemView) {
            super(itemView);
            textMessage = itemView.findViewById(R.id.title_create_account);
            textView = itemView.findViewById(R.id.tv_or);

            textViewSignIn = itemView.findViewById(R.id.tv_sign_in);
            createAccountButton = itemView.findViewById(R.id.create_account_button);


        }
    }


}
