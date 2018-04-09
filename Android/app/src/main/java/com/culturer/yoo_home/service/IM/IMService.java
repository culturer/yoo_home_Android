package com.culturer.yoo_home.service.IM;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.culturer.yoo_home.util.ThreadUtil;
import com.tim.client.Client;
import com.tim.client.ClientFactory;
import com.tim.client.TPClient;
import com.tim.client.TidEnum;
import com.tim.client.TimType;
import com.tim.config.Config;
import com.tim.exception.TimException;
import com.tim.listener.AckListener;
import com.tim.listener.MessageListener;
import com.tim.listener.PresenceListener;
import com.tim.log.Log;
import com.tim.log.LogLevel;
import com.tim.packet.Tid;
import com.tim.packet.TimAckBean;
import com.tim.packet.TimMBean;
import com.tim.packet.TimNode;
import com.tim.packet.TimPBean;
import com.tim.packet.TimTime;


import java.util.Date;
import java.util.List;

import static com.culturer.yoo_home.config.Urls.HOST;

public class IMService extends Service{
	
	private static  final String TAG = "IMService";
	
	Client client;
	String domain = "wuxiaodong";
	String username = "Song";
	String password = "78901214";
	String token = "token";
	
	public IMService() {
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		ThreadUtil.startThread(() -> init());
		return super.onStartCommand(intent, flags, startId);
	}
	
	private void init(){
		initBaseData();
		initIM();
	}
	
	//初始化基础参数
	private void initBaseData(){
		domain = "wuxiaodong";
		username = "Song";
		password  ="78901214";
		token = "token";
	}
	
	//初始化IM服务
	private void initIM() {
		//设置日志等级
		Config.setLogLevel(LogLevel.INFO);
		Config config = new Config();
		//设置域名
		config.setDomain(domain);
		//设置心跳
		config.setHeartbeat(40);
		//设置IP地址
		config.setIp(HOST);
		//设置端口号
		config.setPort(3737);
		//设备信息
		config.setResource(token);
		//自动重连
		config.setReconnectionAllowed(true);
		//传输TLS加密
		config.setTLS(true); // 用TLS传输
		config.setTsslPort(5757); // 服务器TLS端口
		TPClient<Client> tp = TPClient.newInstance(config);
		try {
			client = tp.getClient(ClientFactory.getClient(config));
			client.addMessageListener(new MessageListenerImpl());
			client.addAckListener(new AckListenerImpl(client));
			client.addPresenceListener(new PresenceListenerImpl());
			login(username,password);
		} catch (TimException e) {
			e.printStackTrace();
		}
	}
	
	private void login(String username,String password){
		try {
			client.login(username,password);
		} catch (TimException e) {
			e.printStackTrace();
		}
	}
	
	private void sendMessage(String toname){
		try {
			client.sendMessage(toname, "hello>>>"+toname , TidEnum.TIDTYPE_NORMA);
		} catch (TimException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	/**
	 * @classDesc 类描述: ack监听实现类
	 */
	static class AckListenerImpl implements AckListener {
		private static final Log logger = Log.getLogger();
		Client client;
		
		public AckListenerImpl(Object... args) {
			client = (Client) args[0];
		}
		
		@Override
		public void processAck(TimAckBean ab) {
//			android.util.Log.i(TAG, "processAck: ");
			if (ab!=null){
				TimType ackType = TimType.getTimType(ab.getAckType());
				String ackStatus = ab.getAckStatus();
				logger.info("ackType:", ackType);
				logger.info("ackStatus:", ackStatus);
				if (ackType != null) {
					switch (ackType) {
						case LOGIN:
							if ("400".equals(ackStatus)) {
								logger.info("用户名或密码错误");
								client.close();
							}
							break;
						case MESSAGE:
							break;
						case PING:
							break;
						case PRESENCE:
							break;
						default:
					}
				}
			}
		}
	}
	
	/**
	 * @classDesc 类描述: message监听实现类
	 */
	static class MessageListenerImpl implements MessageListener {
		private static final Log logger = Log.getLogger();
		
		public MessageListenerImpl(Object... args) {
		}
		
		@Override
		public void processMessage(TimMBean mbean) {
			String type = mbean.getType(); // chat
			short msgType = mbean.getMsgType(); // 1 文字 2图片 3音频 4视频
			String mid = mbean.getMid(); // 信息id
			String timestamp = mbean.getTimestamp();
			Date date = new Date(Long.valueOf(timestamp)); // 发信时间
			Tid fromtid = mbean.getFromTid(); // 发信者tid
			String name = fromtid.getName(); // 发信登陆名
			TimTime tt = mbean.getOffline();
			if (tt != null) {
				// 说明是离线信息
			}
			List<TimNode> extraList = mbean.getExtraList(); // 附加字段
			logger.info(type);
			logger.info(msgType);
			logger.info(mbean.getBody());
			logger.info(mid);
			logger.info(date);
			logger.info(name);
			logger.info(tt);
			logger.info(extraList);
		}
		
		@Override
		public void loadMessage(TimMBean mbean) {
			logger.info(mbean.toString());
		}
		
		@Override
		public void processMessage(List<TimMBean> mbeans) {
			if (mbeans != null && mbeans.size() > 0) {
				for (TimMBean tm : mbeans) {
					processMessage(tm);
				}
			}
		}
	}
	
	static class PresenceListenerImpl implements PresenceListener {
		private static final Log logger = Log.getLogger();
		
		@Override
		public void processPresence(TimPBean pbean) {
			if (pbean != null) {
				logger.info("在线状态:", pbean.toString());
			}
		}
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
}
