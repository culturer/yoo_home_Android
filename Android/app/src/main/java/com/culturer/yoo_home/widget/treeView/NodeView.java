package com.culturer.yoo_home.widget.treeView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.culturer.yoo_home.R;
import com.culturer.yoo_home.widget.treeView.model.NodeModel;
//import com.owant.thinkmap.R;



/**
 * Created by owant on 09/02/2017.
 */
@SuppressLint("AppCompatCustomView")
public class NodeView extends TextView {

    public NodeModel<String> treeNode = null;

    public NodeView(Context context) {
        this(context, null, 0);
    }

    public NodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setTextColor(Color.WHITE);
        setPadding(12, 10, 12, 10);

        Drawable drawable = context.getDrawable(R.drawable.node_view_bg);
        setBackgroundDrawable(drawable);
    }

    public NodeModel<String> getTreeNode() {
        return treeNode;
    }

    public void setTreeNode(NodeModel<String> treeNode) {
        this.treeNode = treeNode;
        setSelected(treeNode.isFocus());
        setText(treeNode.getValue());
    }

}