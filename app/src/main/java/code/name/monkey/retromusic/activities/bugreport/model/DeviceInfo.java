package code.name.monkey.retromusic.activities.bugreport.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.IntRange;
import code.name.monkey.retromusic.util.PreferenceUtil;
import java.util.Arrays;
import java.util.Locale;

public class DeviceInfo {

  @SuppressLint("NewApi")
  @SuppressWarnings("deprecation")
  private static final String[] abis =
      Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
          ? Build.SUPPORTED_ABIS
          : new String[] {Build.CPU_ABI, Build.CPU_ABI2};

  @SuppressLint("NewApi")
  private static final String[] abis32Bits =
      Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? Build.SUPPORTED_32_BIT_ABIS : null;

  @SuppressLint("NewApi")
  private static final String[] abis64Bits =
      Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? Build.SUPPORTED_64_BIT_ABIS : null;

  private final String baseTheme;

  private static final String BRAND = Build.BRAND;

  private static final String BUILD_ID = Build.DISPLAY;

  private static final String BUILD_VERSION = Build.VERSION.INCREMENTAL;

  private static final String DEVICE = Build.DEVICE;

  private static final String HARDWARE = Build.HARDWARE;

  private final boolean isAdaptive;

  private static final String MANUFACTURER = Build.MANUFACTURER;

  private static final String MODEL = Build.MODEL;

  private final String nowPlayingTheme;

  private static final String PRODUCT = Build.PRODUCT;

  private static final String RELEASE_VERSION = Build.VERSION.RELEASE;

  @IntRange(from = 0)
  private static final int SDK_VERSION = Build.VERSION.SDK_INT;

  private final int versionCode;

  private final String versionName;
  private final String selectedLang;

  public DeviceInfo(Context context) {
    PackageInfo packageInfo;
    try {
      packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
    } catch (PackageManager.NameNotFoundException e) {
      packageInfo = null;
    }
    if (packageInfo != null) {
      versionCode = packageInfo.versionCode;
      versionName = packageInfo.versionName;
    } else {
      versionCode = -1;
      versionName = null;
    }
    baseTheme = PreferenceUtil.INSTANCE.getBaseTheme();
    nowPlayingTheme =
        context.getString(PreferenceUtil.INSTANCE.getNowPlayingScreen().getTitleRes());
    isAdaptive = PreferenceUtil.INSTANCE.isAdaptiveColor();
    selectedLang = PreferenceUtil.INSTANCE.getLanguageCode();
  }

  public String toMarkdown() {
    return "Device info:\n"
        + "---\n"
        + "<table>\n"
        + "<tr><td><b>App version</b></td><td>"
        + versionName
        + "</td></tr>\n"
        + "<tr><td>App version code</td><td>"
        + versionCode
        + "</td></tr>\n"
        + "<tr><td>Android build version</td><td>"
        + BUILD_VERSION
        + "</td></tr>\n"
        + "<tr><td>Android release version</td><td>"
        + RELEASE_VERSION
        + "</td></tr>\n"
        + "<tr><td>Android SDK version</td><td>"
        + SDK_VERSION
        + "</td></tr>\n"
        + "<tr><td>Android build ID</td><td>"
        + BUILD_ID
        + "</td></tr>\n"
        + "<tr><td>Device brand</td><td>"
        + BRAND
        + "</td></tr>\n"
        + "<tr><td>Device manufacturer</td><td>"
        + MANUFACTURER
        + "</td></tr>\n"
        + "<tr><td>Device name</td><td>"
        + DEVICE
        + "</td></tr>\n"
        + "<tr><td>Device model</td><td>"
        + MODEL
        + "</td></tr>\n"
        + "<tr><td>Device product name</td><td>"
        + PRODUCT
        + "</td></tr>\n"
        + "<tr><td>Device hardware name</td><td>"
        + HARDWARE
        + "</td></tr>\n"
        + "<tr><td>ABIs</td><td>"
        + Arrays.toString(abis)
        + "</td></tr>\n"
        + "<tr><td>ABIs (32bit)</td><td>"
        + Arrays.toString(abis32Bits)
        + "</td></tr>\n"
        + "<tr><td>ABIs (64bit)</td><td>"
        + Arrays.toString(abis64Bits)
        + "</td></tr>\n"
        + "<tr><td>Language</td><td>"
        + selectedLang
        + "</td></tr>\n"
        + "</table>\n";
  }

  @Override
  public String toString() {
    return "App version: "
        + versionName
        + "\n"
        + "App version code: "
        + versionCode
        + "\n"
        + "Android build version: "
        + BUILD_VERSION
        + "\n"
        + "Android release version: "
        + RELEASE_VERSION
        + "\n"
        + "Android SDK version: "
        + SDK_VERSION
        + "\n"
        + "Android build ID: "
        + BUILD_ID
        + "\n"
        + "Device brand: "
        + BRAND
        + "\n"
        + "Device manufacturer: "
        + MANUFACTURER
        + "\n"
        + "Device name: "
        + DEVICE
        + "\n"
        + "Device model: "
        + MODEL
        + "\n"
        + "Device product name: "
        + PRODUCT
        + "\n"
        + "Device hardware name: "
        + HARDWARE
        + "\n"
        + "ABIs: "
        + Arrays.toString(abis)
        + "\n"
        + "ABIs (32bit): "
        + Arrays.toString(abis32Bits)
        + "\n"
        + "ABIs (64bit): "
        + Arrays.toString(abis64Bits)
        + "\n"
        + "Base theme: "
        + baseTheme
        + "\n"
        + "Now playing theme: "
        + nowPlayingTheme
        + "\n"
        + "Adaptive: "
        + isAdaptive
        + "\n"
        + "System language: "
        + Locale.getDefault().toLanguageTag()
        + "\n"
        + "In-App Language: "
        + selectedLang;
  }
}
