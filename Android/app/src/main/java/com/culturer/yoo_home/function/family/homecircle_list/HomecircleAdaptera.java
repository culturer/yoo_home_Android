package com.culturer.yoo_home.function.family.homecircle_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.culturer.yoo_home.R;
import com.culturer.yoo_home.bean.Article;
import com.culturer.yoo_home.bean.Comment;
import com.culturer.yoo_home.bean.Photo;
import com.culturer.yoo_home.bean.User;

import java.util.ArrayList;
import java.util.List;


//太复杂
//需要废弃掉
//等旧代码移植成功后该代码就将废弃

public class HomecircleAdaptera extends   RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private List<Article> articles  = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
    private List<Photo> photos = new ArrayList<>();
    private List<User> familyUsers = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    HomecircleAdaptera(List<Article> articles, List<Comment> comments , List<Photo> photos , List<User> familyUsers, Context context) {
        this.articles = articles;
        this.comments = comments;
        this.photos = photos;
        this.familyUsers = familyUsers;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
//        String chatMsg = items.get(position);
//        if (chatMsg!=null && chatMsg.getUserId()!=0 && BaseMsg.getUser()!=null){
//            if (chatMsg.getUserId() == BaseMsg.getUser().getId()){
//                return 0;
//            }else {
//                return 1;
//            }
//        }else {
//            return 0;
//        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == 0){
            itemView = inflater.inflate(R.layout.homecircle_item,parent,false);
        }else {
            itemView = inflater.inflate(R.layout.homecircle_item,parent,false);
        }
        if (itemView!=null){
            ViewHolder1 viewHolder1 = new ViewHolder1(itemView);
            return viewHolder1;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
//        Log.i("adapter", "onBindViewHolder: "+articles.get(position).toString());
//        if (holder instanceof ViewHolder1 ){
//            final Article article = articles.get(position);
//            //设置用户信息
//            for (int i = 0 ;i<familyUsers.size();i++){
//                if (familyUsers.get(i).getId() == article.getUserId()){
//                    //设置头像
//                    GlideApp.with(context).
//                            load(familyUsers.get(i).getIcon()).
//                            diskCacheStrategy(DiskCacheStrategy.RESOURCE).
//                            apply(RequestOptions.bitmapTransform(new CircleCrop())).
//                            thumbnail(0.5f).
//                            placeholder(R.drawable.logo_black).
//                            priority(Priority.LOW).
//                            error(R.drawable.logo_black).
//                            fallback(R.drawable.logo_black).
//                            into( ((ViewHolder1) holder).usericon);
//                    //设置姓名
//                    ((ViewHolder1) holder).username.setText(familyUsers.get(i).getUsername());
//                }
//            }
//            //设置动态信息
//            ((ViewHolder1) holder).time.setText(article.getCreateTime());
//            ((ViewHolder1) holder).content.setText(article.getDesc());
//            //设置图片
//            List<Photo> pictures = new ArrayList<>();
//            for (int i=0 ; i<photos.size();i++){
//                //筛选出需要的图片
//                if (photos.get(i).getArticleId() == article.getId()){
//                    pictures.add(photos.get(i));
//                }
//            }
//            //设置9宫格图片显示
//            if (pictures.size() == 0){
//                ((ViewHolder1) holder).pictures.setVisibility(View.GONE);
//            }else if (pictures.size() == 1){
//                ((ViewHolder1) holder).pictures.setNumColumns(1);
//            }else if (pictures.size() == 2 || pictures.size() == 4){
//                ((ViewHolder1) holder).pictures.setNumColumns(2);
//            }else{
//                ((ViewHolder1) holder).pictures.setNumColumns(3);
//            }
//            SimpleAdapter pictureAdapter = new SimpleAdapter(context,new ArrayList<Map<String, String>>(),-1,new String[]{},new int[]{});
//            ((ViewHolder1) holder).pictures.setAdapter(pictureAdapter);
//            //设置评论列表
//            List<Map<String,String>> myComments = new ArrayList<>();
//            for (int i=0 ;i<comments.size();i++){
//                if (comments.get(i).getArticleId() == article.getId()){
//                    Map<String,String> map = new HashMap<>();
//                    map.put("commentId",comments.get(i).getCommentId().toString());
//                    map.put("createTime",comments.get(i).getCreateTime());
//                    map.put("desc",comments.get(i).getDesc());
//                    map.put("user",comments.get(i).getUserId().toString());
//                    myComments.add(map);
//                }
//            }
//            SimpleAdapter commentAdapter = new SimpleAdapter(context,myComments,-1,new String[]{},new int[]{});
//            ((ViewHolder1) holder).comments.setAdapter(commentAdapter);
//            //处理点赞按钮
//            ((ViewHolder1) holder).favorite_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                }
//            });
//            ((ViewHolder1) holder).favorite_txt.setText("隔壁老宋宋觉得很赞");
//            //处理评论按钮
//            ((ViewHolder1) holder).comment_ok.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Comment myComment = new Comment(0l,article.getId(),0l, (long)BaseMsg.getUser().getId(), ((ViewHolder1) holder).comment_et.getText().toString(), TimeUtil.getCurrentTime());
//                    //发送评论
//                }
//            });
//        }
        
        List<String> datas = new ArrayList<>();
        for (int i = 0;i<9;i++){
            datas.add("data"+i);
        }
        PictureAdapter pictureAdapter = new PictureAdapter(context,datas);
        ((ViewHolder1) holder).pictures.setNumColumns(3);
        ((ViewHolder1) holder).pictures.setAdapter(pictureAdapter);
    
    }

    @Override
    public int getItemCount() {
//        return articles.size();
        return 20;
    }

    //更新数据源
    public void setDataAndupdate(List<Article> datas){
        this.articles = datas;
        notifyDataSetChanged();
    }

    class ViewHolder1 extends RecyclerView.ViewHolder{

        View itemView ;
        //头像
        ImageView usericon;
        //用户名
        TextView username;
        //时间
        TextView time;
        //内容
        TextView content;
        //图片
        GridView pictures;
        //赞
        CheckBox favorite_btn;
        TextView favorite_txt;
        //评论列表
        ListView comments;
        //评论
        EditText comment_et;
        //发表评论
        ImageButton comment_ok;

        ViewHolder1(View itemView) {
            super(itemView);
            this.itemView = itemView;
            init();
        }

        private void init(){
            usericon = itemView.findViewById(R.id.usericon);
            username = itemView.findViewById(R.id.username);
            time = itemView.findViewById(R.id.time);
            content = itemView.findViewById(R.id.content);
            pictures = itemView.findViewById(R.id.pictures);
            favorite_btn = itemView.findViewById(R.id.favorite_btn);
            favorite_txt = itemView.findViewById(R.id.favorite_txt);
            comment_et = itemView.findViewById(R.id.comment_et);
            comments = itemView.findViewById(R.id.comments);
            comment_ok = itemView.findViewById(R.id.comment_ok);
        }

    }

}
