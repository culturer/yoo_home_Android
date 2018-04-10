package com.culturer.yoo_home.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.widget.treeView.RightTreeLayoutManager;
import com.culturer.yoo_home.widget.treeView.TreeView;
import com.culturer.yoo_home.widget.treeView.model.NodeModel;
import com.culturer.yoo_home.widget.treeView.model.TreeModel;
import com.culturer.yoo_home.widget.treeView.util.DensityUtils;

public class TestTreeViewActivity extends AppCompatActivity {
	
	private TreeView treeView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_tree_view);
		init();
	}
	
	private void init(){
		treeView = findViewById(R.id.treeView);
		final NodeModel<String> nodeA = new NodeModel<>("家庭A");
		final NodeModel<String> nodeB = new NodeModel<>("家庭B");
		final NodeModel<String> nodeC = new NodeModel<>("家庭C");
		final NodeModel<String> nodeD = new NodeModel<>("家庭D");
		final NodeModel<String> nodeE = new NodeModel<>("家庭E");
		final NodeModel<String> nodeF = new NodeModel<>("家庭F");
		final NodeModel<String> nodeG = new NodeModel<>("家庭G");
		final NodeModel<String> nodeH = new NodeModel<>("家庭H");
		final NodeModel<String> nodeI = new NodeModel<>("家庭I");
		final NodeModel<String> nodeJ = new NodeModel<>("家庭J");
		final NodeModel<String> nodeK = new NodeModel<>("家庭K");
		final NodeModel<String> nodeL = new NodeModel<>("家庭L");
		final NodeModel<String> nodeM = new NodeModel<>("家庭M");
		final NodeModel<String> nodeN = new NodeModel<>("家庭N");
		final NodeModel<String> nodeO = new NodeModel<>("家庭O");
		final NodeModel<String> nodeP = new NodeModel<>("家庭P");
		final NodeModel<String> nodeQ = new NodeModel<>("家庭Q");
		final NodeModel<String> nodeR = new NodeModel<>("家庭R");
		final NodeModel<String> nodeS = new NodeModel<>("家庭S");
		final NodeModel<String> nodeT = new NodeModel<>("家庭T");
		final NodeModel<String> nodeU = new NodeModel<>("家庭U");
		final NodeModel<String> nodeV = new NodeModel<>("家庭V");
		final NodeModel<String> nodeW = new NodeModel<>("家庭W");
		final NodeModel<String> nodeX = new NodeModel<>("家庭X");
		final NodeModel<String> nodeY = new NodeModel<>("家庭Y");
		final NodeModel<String> nodeZ = new NodeModel<>("家庭Z");
		
		
		final TreeModel<String> tree = new TreeModel<>(nodeA);
		tree.addNode(nodeA, nodeB, nodeC, nodeD);
		tree.addNode(nodeC, nodeE, nodeF, nodeG, nodeH, nodeI);
		tree.addNode(nodeB, nodeJ, nodeK, nodeL);
		tree.addNode(nodeD, nodeM, nodeN, nodeO);
		tree.addNode(nodeF, nodeP, nodeQ, nodeR, nodeS);
		tree.addNode(nodeR, nodeT, nodeU, nodeV, nodeW, nodeX);
		tree.addNode(nodeT, nodeY, nodeZ);
		
		int dx = DensityUtils.dp2px(this, 20);
		int dy = DensityUtils.dp2px(this, 20);
		int mHeight = DensityUtils.dp2px(this, 720);
		
		treeView.setTreeLayoutManager(new RightTreeLayoutManager(dx, dy, mHeight));
		treeView.setTreeModel(tree);
	}
}
