/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50506
Source Host           : localhost:3306
Source Database       : idiomdb

Target Server Type    : MYSQL
Target Server Version : 50506
File Encoding         : 65001

Date: 2021-01-24 23:17:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tbl_gameconn`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gameconn`;
CREATE TABLE `tbl_gameconn` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nowset` int(11) DEFAULT '1',
  `userId` int(11) DEFAULT '-1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_gameconn
-- ----------------------------
INSERT INTO `tbl_gameconn` VALUES ('1', '2', '3');
INSERT INTO `tbl_gameconn` VALUES ('2', '1', '4');
INSERT INTO `tbl_gameconn` VALUES ('3', '2', '8');

-- ----------------------------
-- Table structure for `tbl_gameguess`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gameguess`;
CREATE TABLE `tbl_gameguess` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nowset` int(11) DEFAULT '1',
  `userId` int(11) DEFAULT '-1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_gameguess
-- ----------------------------
INSERT INTO `tbl_gameguess` VALUES ('1', '4', '3');
INSERT INTO `tbl_gameguess` VALUES ('3', '2', '4');
INSERT INTO `tbl_gameguess` VALUES ('4', '3', '8');

-- ----------------------------
-- Table structure for `tbl_idiom`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_idiom`;
CREATE TABLE `tbl_idiom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `pronounce` varchar(20) DEFAULT NULL,
  `antonym` varchar(20) DEFAULT NULL,
  `homoionym` varchar(20) DEFAULT NULL,
  `derivation` varchar(50) DEFAULT NULL,
  `example` varchar(50) DEFAULT NULL,
  `explain` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_idiom
-- ----------------------------
INSERT INTO `tbl_idiom` VALUES ('1', 'shui行云流水', '文章类', '矫揉造作', '挥洒自如', '《后汉书》', '真正的好文章如行云流水一般。', '比喻文章真实不做作。');
INSERT INTO `tbl_idiom` VALUES ('2', '出水芙蓉', '人物外貌', '花容月貌', '平淡无奇', '《诗经》', '小姑娘的皮肤如出水芙蓉一般。', '比喻皮肤十分细腻美好。');
INSERT INTO `tbl_idiom` VALUES ('3', '杯弓蛇影', '寓言类', '谈笑自若', '风声鹤唳', '《晋书·乐广传》记载有人作客饮酒，见酒杯里有条蛇，喝后吓得病了。后来知道这蛇原来是屋角上一张弓照在杯', '黑夜走路，千万别杯弓蛇影，那样只会自己吓自己。', '将映在酒杯里的弓影误认为蛇。比喻因疑神疑鬼而引起恐惧。');
INSERT INTO `tbl_idiom` VALUES ('4', '打草惊蛇', '寓言类', '引蛇出洞', '因小失大', '明·郎瑛《七修类稿》卷二十四：“打草惊蛇，乃南唐王鲁为当涂令，日营资产，部人诉主簿贪污，鲁曰：‘汝虽', '空自去打草惊蛇，倒吃他做了手脚，却是不好。', '打草惊了草里的蛇。原比喻惩罚了甲而使乙有所警觉。后多比喻做法不谨慎，反使对方有所戒备。');
INSERT INTO `tbl_idiom` VALUES ('5', '封豕长蛇', '寓言类', '祥麟威凤', '牛鬼蛇神', '封：大；封豕：大猪；长蛇：大蛇。贪婪如大猪，残暴如大蛇。比喻贪暴者、侵略者。', '而今而后，所当有利兵坚盾，环卫其身，毋俾封豕长蛇，荐食上国。（鲁迅《集外集拾遗·破恶声论》）', '封：大；封豕：大猪；长蛇：大蛇。贪婪如大猪，残暴如大蛇。比喻贪暴者、侵略者。');
INSERT INTO `tbl_idiom` VALUES ('6', '虚与委蛇', '寓言类', '真心实意', '虚情假意', '《庄子·应帝王》：“乡吾示之以未始出吾宗，吾与之虚而委蛇。”', '他也要同你虚与委蛇了。（闻一多《诗与批评·戏剧的歧途》）', '虚：假；委蛇：随便应顺。指对人虚情假意，敷衍应酬。');
INSERT INTO `tbl_idiom` VALUES ('7', '老马识途', '寓言类', '乳臭未干', '轻车熟路', '《韩非子·说林上》：“管仲、隰朋从于桓公伐孤竹，春往冬返，迷惑失道。管仲曰：‘老马之智可用也。’乃放', '老马识途添病骨,穷猿投树择深枝。——清·黄景仁《两当轩集》', '途:路，道路。老马认识曾经走过的道路。比喻有经验的人对事情比较熟悉。 ');
INSERT INTO `tbl_idiom` VALUES ('8', '亡羊补牢', '寓言类', '时不我待', '知错就改', '《战国策·楚策》：“见兔而顾犬，未为晚也；亡羊而补牢，未为迟也。”', '愚有一计：并不劳亡羊补牢，纳士献印。（明·罗贯中《三国演义》第四十四回）', '亡：逃亡，丢失；牢：关牲口的圈。羊逃跑了再去修补羊圈，还不算晚。比喻出了问题以后想办法补救，可以防止');
INSERT INTO `tbl_idiom` VALUES ('9', '羊狠狼贪', '动物类', '廉洁奉公', '羊狠狼贪', '《史记·项羽本纪》：“因下令军中曰：‘猛如虎，很如羊，贪如狼，强不可使者，皆斩之。’”', '羊狠狼贪，竟玷人臣之节。', '狠：凶狠。原指为人凶狠，争夺权势。后比喻贪官污吏的残酷剥削。');
INSERT INTO `tbl_idiom` VALUES ('10', '尖嘴猴腮', '动物类', '风流潇洒', '丑态毕露', '清·吴敬梓《儒林外史》第三回：“象你这尖嘴猴腮，也该撒泡尿自己照照！不三不四，就想天鹅屁吃。”', '看你这尖嘴猴腮的样子。', '腮：面颊。尖嘴巴，瘦面颊。形容人相貌丑陋粗俗。');
INSERT INTO `tbl_idiom` VALUES ('11', '狼心狗肺', '动物类', '碧血丹心', '人面兽心', '明·冯梦龙《醒世恒言》卷三十：“那知这贼子恁般狼心狗肺，负恩忘义。”', '你这个狼心狗肺的人，你想把我气死了，你们去过快活日子。', '形容心肠象狼和狗一样凶恶狠毒。');
INSERT INTO `tbl_idiom` VALUES ('12', '白云苍狗', '动物类', '一成不变', '变换无常', '唐·杜甫《可叹诗》：“天上浮云似白衣，斯须改变如苍狗。”', '真是世事变换无常，不禁感慨系之矣！（鲁迅《华盖集后记》）', '苍：灰白色。浮云象白衣裳，顷刻又变得象苍狗。比喻事物变化不定。');
INSERT INTO `tbl_idiom` VALUES ('13', '独鹤鸡群', '动物类', '相形见绌', '出类拔萃', '晋·戴逵《竹林七贤论》：“嵇绍入洛，或谓王戎曰：‘昨于稠人中始见嵇绍，昂昂然若野鹤之在鸡群。’”南朝', '独鹤鸡群自寡俦，三间老屋日西头。（清·钱谦益《客途有怀吴中故人周吏部景文》）', '一只鹤站在鸡群中。比喻一个人的才能或仪表超群出众。');
INSERT INTO `tbl_idiom` VALUES ('14', '呆若木鸡', '人物神态类', '神色自若', '呆头呆脑', '《庄子·达生》：“几矣。鸡虽有鸣者，已无变矣，望之似木鸡矣，其德全矣；异鸡无敢应者，反走矣。”', '匪首侯殿坤，在得知这个噩耗之后，特别是知道了老妖道的落网后，当即呆若木鸡。（曲波《林海雪原》二八）', '呆：傻，发愣的样子。呆得象木头鸡一样。形容因恐惧或惊异而发愣的样子。');
INSERT INTO `tbl_idiom` VALUES ('15', '缚鸡之力', '人物神态类', '回天之力', '力不能支', '元·《赚蒯通》第一折：“那韩信手无缚鸡之力。”', '平日只会读书写字，刺绣描花，手无缚鸡之力。', '捆鸡的力量。比喻体弱无力。');
INSERT INTO `tbl_idiom` VALUES ('16', '鸡飞蛋打', '事情结果类', '一举两得 ', '两败俱伤', '清·蒲松龄《聊斋志异·阿霞》：“人之无良，舍其旧而新是谋，卒之卵覆鸟亦飞，天之所报亦惨矣。”', '只要他一进关抄了咱们的后路，那就鸡飞蛋打，不可收拾了。 ◎周骥良《吉鸿昌》', '鸡飞走了，蛋打破了。比喻两头落空，一无所得。');
INSERT INTO `tbl_idiom` VALUES ('17', '鸡犬不留', '事情结果类', '秋毫无犯', '斩尽杀绝', '《三国志·荀彧传》：“引军从泗南攻取虑、睢陵、夏丘诸县，皆屠之，鸡犬亦尽，墟邑无复行人。” ', '这是一笔永远算不清的债！以言杀戮，确是斩尽杀绝。 ◎老舍《吐了一口气》', '形容屠杀残酷，连鸡狗都不能幸免。');
INSERT INTO `tbl_idiom` VALUES ('18', '杀鸡取卵', '寓言类', '高瞻远瞩', '饮鸩止渴', '《伊索寓言》', '请皇上勿再竭泽而渔，杀鸡取卵，为小民留一线生机。（姚雪垠《李自成》第二卷第三十二章）', '卵：蛋。为了要得到鸡蛋，不惜把鸡杀了。比喻贪图眼前的好处而不顾长远利益。');
INSERT INTO `tbl_idiom` VALUES ('19', '闻鸡起舞', '寓言类', '苟且偷安', '发奋图强', '　《晋书·祖逖传》：“中夜闻荒鸡鸣，蹴琨觉，曰：‘此非恶声也。’因起舞。” 　　《资治通鉴》：“中夜', '击楫誓清，毕竟英雄得。', '听到鸡叫就起来舞剑，后比喻有志报国的人即时奋起。');
INSERT INTO `tbl_idiom` VALUES ('20', '鹿死谁手', '寓言类', '和平共处', '明争暗斗', '《晋书·石勒载记下》：“朕若逢高皇，当北面而事之，与韩、彭鞭而争先耳；脱遇光武，当并驱于中原，未知鹿', '古人把争天下比做“逐鹿中原”。也只有稳据中原，才能定鹿死谁手。（姚雪垠《李自成》第二卷第四十七章）', '原比喻不知政权会落在谁的手里。现在也泛指在竞赛中不知谁会取得最后的胜利。');

-- ----------------------------
-- Table structure for `tbl_user`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES ('1', '张三', '123');
INSERT INTO `tbl_user` VALUES ('2', '李四', '123');
INSERT INTO `tbl_user` VALUES ('3', 'zs', '123');
INSERT INTO `tbl_user` VALUES ('4', 'lisi', '123');
INSERT INTO `tbl_user` VALUES ('8', 'ww', '123');
