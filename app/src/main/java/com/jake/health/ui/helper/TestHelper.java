
package com.jake.health.ui.helper;

import android.widget.ListAdapter;

import com.jake.health.entity.AnalysisInfo;
import com.jake.health.entity.HomeNavInfo;
import com.jake.health.entity.HospitalInfo;
import com.jake.health.entity.MineMenuInfo;
import com.jake.health.entity.QAInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：测试相关
 *
 * @author jakechen
 * @since 2016/4/29 11:27
 */
public class TestHelper {

    public static List<QAInfo> getTestQA() {
        List<QAInfo> list = new ArrayList<>();
        QAInfo info = new QAInfo();
        info.setAvatar("http://img3.imgtn.bdimg.com/it/u=1011027398,356111671&fm=21&gp=0.jpg");
        info.setNickname("漂亮的不像实力派");
        info.setTitle("科比来自哪里?");
        info.setZanNum(12340);
        info.setContent("科比童年科比童年科比·布莱恩特于1978年8月23日出生在美国宾夕法尼亚州费城，是前NBA球员及前洛杉矶火花队主教练乔·布莱恩特（Joe “Jellybean” Bryant）科比父子科比父子和帕梅拉·考克斯·布莱恩特（Pamela Cox Bryant）三个孩子中最小的一个也是唯一的儿子。[4-5]  他的父母在他出生前为他取名Kobe--一种日本牛排[3]  的名字， 是在一家餐馆的菜单上看到的。[4]  科比有两个姐姐，西莉亚和沙雅。科比的父亲乔，在NBA效力8个赛季");
        list.add(info);
        info = new QAInfo();
        info.setAvatar("http://img1.imgtn.bdimg.com/it/u=3767588862,2668021829&fm=21&gp=0.jpg");
        info.setNickname("我是科比");
        info.setTitle("科比来自与宇宙银河太阳系地球美洲美国洛杉矶的哪里?");
        info.setZanNum(140);
        info.setContent("在位于费城郊区高中时期的科比的劳尔梅里恩的劳尔梅里恩高中，科比凭借惊人的高中生涯赢得了全美国的认可。作为一个新人，科比就可以在学校（三年级和四年级）篮球队出任首发。");
        list.add(info);
        for (int i = 0; i < 8; i++) {
            info = new QAInfo();
            info.setAvatar("http://img1.imgtn.bdimg.com/it/u=435937637,1527161840&fm=21&gp=0.jpg");
            info.setNickname("我是批量new出来的");
            info.setZanNum(990 + i);
            info.setTitle("我是批量new出来的科比来自与宇宙银河太阳系地球美洲美国洛杉矶的哪里?");
            info.setContent("在位于费城郊区高中时期的科比的劳尔梅里恩的劳尔梅里恩高中，科比凭借惊人的高中生涯赢得了全美国的认可。作为一个新人，科比就可以在学校（三年级和四年级）篮球队出任首发。");
            list.add(info);
        }
        return list;
    }

    public static List<AnalysisInfo> getTestAnalysis() {
        List<AnalysisInfo> list = new ArrayList<>();
        AnalysisInfo info = new AnalysisInfo();
        info.setAvatar("http://img3.imgtn.bdimg.com/it/u=1011027398,356111671&fm=21&gp=0.jpg");
        info.setNickname("漂亮的不像实力派");
        info.setTitle("感冒（风热感冒）");
        info.setZanNum(12340);
        info.setDate(System.currentTimeMillis());
        info.setSummary("科比童年科比童年科比·布莱恩特于1978年8月23日出生在美国宾夕法尼亚州费城，是前NBA球员及前洛杉矶火花队主教练乔·布莱恩特（Joe “Jellybean” Bryant）科比父子科比父子和帕梅拉·考克斯·布莱恩特（Pamela Cox Bryant）三个孩子中最小的一个也是唯一的儿子。[4-5]  他的父母在他出生前为他取名Kobe--一种日本牛排[3]  的名字， 是在一家餐馆的菜单上看到的。[4]  科比有两个姐姐，西莉亚和沙雅。科比的父亲乔，在NBA效力8个赛季");
        list.add(info);
        info = new AnalysisInfo();
        info.setAvatar("http://img1.imgtn.bdimg.com/it/u=3767588862,2668021829&fm=21&gp=0.jpg");
        info.setNickname("人生如戏");
        info.setTitle("急性阑尾炎");
        info.setZanNum(140);
        info.setSummary("在位于费城郊区高中时期的科比的劳尔梅里恩的劳尔梅里恩高中，科比凭借惊人的高中生涯赢得了全美国的认可。作为一个新人，科比就可以在学校（三年级和四年级）篮球队出任首发。");
        info.setDate(System.currentTimeMillis());
        list.add(info);
        for (int i = 0; i < 8; i++) {
            info = new AnalysisInfo();
            info.setAvatar("http://img1.imgtn.bdimg.com/it/u=435937637,1527161840&fm=21&gp=0.jpg");
            info.setNickname("我是超级医生");
            info.setZanNum(990 + i);
            info.setDate(System.currentTimeMillis());
            info.setTitle("急性肠道炎");
            info.setSummary("在位于费城郊区高中时期的科比的劳尔梅里恩的劳尔梅里恩高中，科比凭借惊人的高中生涯赢得了全美国的认可。作为一个新人，科比就可以在学校（三年级和四年级）篮球队出任首发。");
            list.add(info);
        }
        return list;
    }

    public static List<HospitalInfo> getTestHospital() {
        List<HospitalInfo> list = new ArrayList<>();
        HospitalInfo info = new HospitalInfo();
        info.setAvatar("http://img3.imgtn.bdimg.com/it/u=1011027398,356111671&fm=21&gp=0.jpg");
        info.setNickname("漂亮的不像实力派");
        info.setTitle("科比来自哪里?");
        info.setZanNum(12340);
        info.setContent("科比童年科比童年科比·布莱恩特于1978年8月23日出生在美国宾夕法尼亚州费城，是前NBA球员及前洛杉矶火花队主教练乔·布莱恩特（Joe “Jellybean” Bryant）科比父子科比父子和帕梅拉·考克斯·布莱恩特（Pamela Cox Bryant）三个孩子中最小的一个也是唯一的儿子。[4-5]  他的父母在他出生前为他取名Kobe--一种日本牛排[3]  的名字， 是在一家餐馆的菜单上看到的。[4]  科比有两个姐姐，西莉亚和沙雅。科比的父亲乔，在NBA效力8个赛季");
        list.add(info);
        info = new HospitalInfo();
        info.setAvatar("http://img1.imgtn.bdimg.com/it/u=3767588862,2668021829&fm=21&gp=0.jpg");
        info.setNickname("我是科比");
        info.setTitle("科比来自与宇宙银河太阳系地球美洲美国洛杉矶的哪里?");
        info.setZanNum(140);
        info.setContent("在位于费城郊区高中时期的科比的劳尔梅里恩的劳尔梅里恩高中，科比凭借惊人的高中生涯赢得了全美国的认可。作为一个新人，科比就可以在学校（三年级和四年级）篮球队出任首发。");
        list.add(info);
        for (int i = 0; i < 8; i++) {
            info = new HospitalInfo();
            info.setAvatar("http://img1.imgtn.bdimg.com/it/u=435937637,1527161840&fm=21&gp=0.jpg");
            info.setNickname("我是批量new出来的");
            info.setZanNum(990 + i);
            info.setTitle("我是批量new出来的科比来自与宇宙银河太阳系地球美洲美国洛杉矶的哪里?");
            info.setContent("在位于费城郊区高中时期的科比的劳尔梅里恩的劳尔梅里恩高中，科比凭借惊人的高中生涯赢得了全美国的认可。作为一个新人，科比就可以在学校（三年级和四年级）篮球队出任首发。");
            list.add(info);
        }
        return list;
    }

    public static ArrayList<?> getTestBanner() {
        ArrayList<String> list = new ArrayList<>();
        list.add("http://img0.imgtn.bdimg.com/it/u=535245040,1392341624&fm=21&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=513166044,2711533450&fm=21&gp=0.jpg");
        list.add("http://img5.imgtn.bdimg.com/it/u=2877222865,4042023416&fm=21&gp=0.jpg");
        list.add("http://img5.imgtn.bdimg.com/it/u=2462868875,1126990464&fm=21&gp=0.jpg");
        return list;
    }

    public static List<HomeNavInfo> getTestNav() {
        List<HomeNavInfo> list = new ArrayList<>();
        HomeNavInfo info = new HomeNavInfo();
        info.setTitle("求医问药");
        info.setHint("有专业人士帮你解答哦");
        info.setShowDot(1);
        info.setType(HomeNavInfo.TYPE_QA);
        info.setIcon("http://img0.imgtn.bdimg.com/it/u=2043863225,3873181157&fm=21&gp=0.jpg");
        list.add(info);
        info = new HomeNavInfo();
        info.setTitle("病理分析");
        info.setHint("好好了解一下病理吧");
        info.setType(HomeNavInfo.TYPE_ANALYSIS);
        info.setIcon("http://img2.imgtn.bdimg.com/it/u=1220192801,959224449&fm=21&gp=0.jpg");
        list.add(info);
        info = new HomeNavInfo();
        info.setTitle("七嘴八舌");
        info.setHint("说出你的不痛快");
        info.setType(HomeNavInfo.TYPE_MOMENTS);
        info.setIcon("http://img3.imgtn.bdimg.com/it/u=4064454169,2329031964&fm=21&gp=0.jpg");
        list.add(info);
        info = new HomeNavInfo();
        info.setType(HomeNavInfo.TYPE_HOSPITAL);
        info.setTitle("附近诊所");
        info.setHint("赶快去医院吧");
        info.setIcon("http://img1.imgtn.bdimg.com/it/u=2227703210,2613438003&fm=21&gp=0.jpg");
        list.add(info);

        return list;
    }

    public static List<MineMenuInfo> getTestMenu() {
        List<MineMenuInfo> list = new ArrayList<>();
        MineMenuInfo info = new MineMenuInfo();
        info.setTitle("消息");
        info.setShowDot(1);
        info.setIcon("http://img2.imgtn.bdimg.com/it/u=1390048268,4118739760&fm=21&gp=0.jpg");
        list.add(info);
        info = new MineMenuInfo();
        info.setTitle("病例");
        info.setIcon("http://img2.imgtn.bdimg.com/it/u=1390048268,4118739760&fm=21&gp=0.jpg");
        list.add(info);
        info = new MineMenuInfo();
        info.setTitle("关注");
        info.setIcon("http://img2.imgtn.bdimg.com/it/u=1390048268,4118739760&fm=21&gp=0.jpg");
        list.add(info);
        info = new MineMenuInfo();
        info.setTitle("粉丝");
        info.setIcon("http://img2.imgtn.bdimg.com/it/u=3119963880,936101450&fm=21&gp=0.jpg");
        list.add(info);
        info = new MineMenuInfo();
        info.setTitle("设置");
        info.setIcon("http://img3.imgtn.bdimg.com/it/u=2832776744,1381723459&fm=21&gp=0.jpg");
        list.add(info);
        return list;
    }

    public static List<QAInfo> loadQAInfoList(ListAdapter adapter, int pageIndex) {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<QAInfo> list = getTestQA();
        if (adapter.getCount() > 30 && pageIndex > 1) {
            list.remove(list.size() - 1);
            list.remove(list.size() - 2);
        }
        return list;
    }

    public static List<AnalysisInfo> loadAnalysisInfoList(ListAdapter adapter, int pageIndex) {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<AnalysisInfo> list = getTestAnalysis();
        if (adapter.getCount() > 30 && pageIndex > 1) {
            list.remove(list.size() - 1);
            list.remove(list.size() - 2);
        }
        return list;
    }
}
