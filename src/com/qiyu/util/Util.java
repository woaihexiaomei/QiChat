package com.qiyu.util;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnKeyListener;
import android.content.SharedPreferences.Editor;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelUuid;
import android.os.StatFs;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

public class Util {

	public static Context mContext;

	public static void showToast(Context context, String msg) {

		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

	}

	public static void gotoActivity(Context context, Class<?> clazz) {
		Intent i = new Intent(context, clazz);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NO_ANIMATION);
		context.startActivity(i);

	}

	public static void gotoActivity(Context context, Class<?> clazz,
			Serializable param) {
		Intent i = new Intent(context, clazz);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NO_ANIMATION);
		i.putExtra("param", param);
		context.startActivity(i);
	}

	/**
	 * 闁跨喐鏋婚幏宄板絿闁跨喕濮ら張顒勬晸閺傘倖瀚�
	 * 
	 * @return
	 */
	public static int getSDK() {

		return android.os.Build.VERSION.SDK_INT;// SDK闁跨喐鏋婚幏锟�

	}

	// 闁告帇鍊栭弻鍥矗閸屾稒娈堕柡鍕靛灠閹焦绋夐搹鍏夋晞
	public static boolean isEmpty(String... strs) {
		if (strs == null || strs.length == 0)
			return true;

		for (String str : strs) {
			if (str == null || str.trim().equals(""))
				return true;
		}
		return false;

	}

	public static ProgressDialog createProgressDialog(final Activity activity,
			String progressDialogTitle, String progressDialogMsg) {
		ProgressDialog progressDialog = new ProgressDialog(activity);

		progressDialog.setTitle(progressDialogTitle);

		progressDialog.setMessage(progressDialogMsg);
		progressDialog.setIndeterminate(false);
		progressDialog.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				activity.onKeyDown(keyCode, event);
				return false;
			}
		});
		// progressDialog.setInverseBackgroundForced(true);
		return progressDialog;
	}

	// 鐠侊紕鐣婚幍褑顢戦弮鍫曟？

	static long startTime;

	public static void startStoreTime() {

		startTime = System.currentTimeMillis();

	}

	public static void endStoreTime() {

		long processtime = System.currentTimeMillis() - startTime;
		System.out.println(processtime);

	}

	public static final String translateTime(float time) {

		int s = (int) time;
		String N = s / (3600 * 1000) + "";
		s = s % (3600 * 1000);
		String K = s / (60 * 1000) + "";
		s = (s % (60 * 1000)) / 1000;
		String M = s + "";

		if (N.length() == 1) {
			N = "0" + N;
		}
		if (K.length() == 1) {
			K = "0" + K;
		}
		if (M.length() == 1) {
			M = "0" + M;
		}
		// System.out.println("閺冨爼妫块弰顖ょ窗" + N + "鐏忓繑妞�" + K + "閸掑棝鎸�" + M + "缁夛拷);
		return N + ":" + K + ":" + M;
	}

	public static void openAllApk1(Context context, String mainactivity,
			String packagename) {
		// 缁旂偞濡ч崷锟�
		Intent mIntent = new Intent();
		mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		ComponentName comp = new ComponentName(mainactivity, packagename);
		mIntent.setComponent(comp);
		mIntent.setAction("android.intent.action.VIEW");
		try {
			context.startActivity(mIntent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getCurrentTime() {

		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");// 閸欘垯浜掗弬閫涚┒閸﹂鎱ㄩ弨瑙勬）閺堢喐鐗稿锟�
		String currenttime = dateFormat.format(now);

		return currenttime;
	}

	public static String getTopActivity(Context context) {

		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
		return cn.getClassName();

	}

	// 閺夛拷甯�潻娑氣柤
	public static void killProgress(Context context, String packageName) {

		Log.e("innog", packageName + "-------killprogress----------");

		ActivityManager mActivityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);

		mActivityManager.killBackgroundProcesses(packageName);

	}

	// 缁崵绮哄☉鍫熶紖闁氨鐓＄拋鍓х枂
	public static void NotificationSet(Context context) {

		if (Build.VERSION.SDK_INT >= 18)
			gotoNotifyservice(context);
		else
			gotoAccessibility(context);

	}

	public static void gotoNotifyservice(Context context) {
		try {
			Intent intent = new Intent(
					"android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
			context.startActivity(intent);
		} catch (ActivityNotFoundException anfe) {
			try {
				Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
				context.startActivity(intent);
			} catch (ActivityNotFoundException anfe2) {
				Toast.makeText(context, anfe2.getMessage(), Toast.LENGTH_LONG)
						.show();
			}
		}
	}

	public static void gotoAccessibility(Context context) {
		try {
			Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
			context.startActivity(intent);
		} catch (ActivityNotFoundException anfe) {
			try {
				Intent intent = new Intent(Settings.ACTION_SETTINGS);
				context.startActivity(intent);
			} catch (ActivityNotFoundException anfe2) {
				Toast.makeText(context, anfe2.getMessage(), Toast.LENGTH_LONG)
						.show();
			}
		}
	}

	/**
	 * Double 淇濈暀2浣嶅皬鏁�涓嬭浇閫熷害
	 */
	public static String getDoubleToStr(double d) {
		DecimalFormat df = new DecimalFormat("#.##");

		return df.format(d);
	}

	/**
	 * 鏄惁瀹夎
	 */
	public static boolean isPkgInstalled(Context context, String packageName) {

		if (packageName == null || "".equals(packageName))
			return false;
		android.content.pm.ApplicationInfo info = null;
		try {
			info = context.getPackageManager().getApplicationInfo(packageName,
					0);

			return info != null;
		} catch (Exception e) {
			return false;

		}
	}

	public static long getSysAllSize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long totalBlocks = stat.getBlockCount();
		return (totalBlocks * blockSize) / 1024 / 1024;// 鍗曚綅MB
	}

	public static long getSysFreeSize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return (availableBlocks * blockSize) / 1024 / 1024;// 鍗曚綅MB
	}

	// get version code
	public static int getVersionCode(Context context, String packageName) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(packageName, 0);
			return info.versionCode;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	// get version name
	public static String getVersionName(Context context, String packageName) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(packageName, 0);
			return info.versionName;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static void setAccountId(Context context, String accountId) {
		SharedPreferences preferencesAccount = context.getSharedPreferences(
				"accountId", Context.MODE_PRIVATE);
		Editor edAccountId = preferencesAccount.edit();
		if (accountId.equals("") || accountId == "") {
			// ed.clear();
			edAccountId.remove("accountId");
		} else {
			edAccountId.putString("accountId", accountId);
		}
		edAccountId.commit();
	}

}
