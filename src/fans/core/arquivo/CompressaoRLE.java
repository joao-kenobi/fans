package fans.core.arquivo;

import java.util.concurrent.TimeUnit;

public class CompressaoRLE {
//	public static void main(String[] args) {
//		String inputString = "aaabbccccd";
//		String outputString = "";
//
//		int count = 1;
//		for (int i = 0; i < inputString.length(); i++) {
//
//			// resetting to 1 for every new character (counting current character).
//			count = 1;
//			while (i < inputString.length() - 1 && inputString.charAt(i) == inputString.charAt(i + 1)) {
//				count++;
//				i++;
//			}
//			outputString = outputString + inputString.charAt(i) + count;
//		}
//		System.out.println("Input data string : " + inputString);
//		System.out.println("Output data string after applying data compression technique : " + outputString);
//	}
	
	public static void main(String[] args) {
		//System.out.println(encode("aaabbccccd"));
		System.out.println(encode("FFE1FF80FFC0FFE07FFCFDFFFFFFFFFFFFE7FB9EFF653F1F04F7639770637C1980E381F3C07FC07FE23FF71FFEFFDFFF1F8BBC049F21CA77E0EFE09FB13303211FF90FFC03FFA2FFD1FFFFFFF7FFFF3F3D3B87C453FF00FB28FB00B7481CFF33FE0FFCBFFE8B7EDF3EFF47FF63FFFEBFFDFBFDBFFC497E139E6D027998FFFE248DFF5DFF2DFF8FFF0DFFF8FFFFFF3FFF00F600F588F350FBE0FF04BF7C68BC7DFF9CFFBEFF9EFF9FFF80FFC0FF80FFA0FF9F77277F0FFF1F7D06717A51BA7FB1FF00FF60FF34FF07FF17FF04FF87FFC3FF7FF76FFEB43D40BED6BFA4576CFF33FF3CFF3CFF30FF19FFF9FFFFFFE7FFF7FFF87FFF7FF3FFDDDF00DFAA9FBDDF16FDFFFF7FFE3FFF3FFE7FFEFFF8FFFEFF82D380835DD5FA22FC5D38121057086A3CFEBCFF7DFFFFFFF9FF78FF7AFF3BFF83BD2AE3FEAA797202C82603382BE04E3FFFBFFFFFFFE7FFE7FFF7DF7FDF3FEF004900C5C698C5D5FC78628AB3CA338B47FF33FFCBFFF1FFE8FFF8FFFCFFF4FF22E445FD307E8019141E00DE031FE84FE1F3F0FDF7FFFFFFFFFB7FFF33FFB8FFDEA4DF48DC9FFFA87FAB33EA006C16D7FFBEF3FFC3FF81FFF9BFF98FFCC7FEE3E5A585950522088AEA5FD2A56C7E170FFF00FF00FF00FFFFFFCFFFEF7FFF3FFF8F988799871D79B8380B1C197C49AC2BFFE7FF43FF03FFFF62FF7AFFFFFFF0FFFEB5FE50FF3E280001FB018F000FC0517FDF7FDF3FFF7EFB7FF9FFF9FFDDFFCF7FD23D553C5D3FCB2FE43F2B7F557F61FFFFFCF7F8FFFDFFFEE7FFF3FFF3FFF10936D805C8CD7A9B1C961E59BF2AFF55FF00FFC09FF807FE04FF46FFF6FFBFFF7FE07FB20FE9C1EC486F115B21DF786AFFBFFF9FFF07FF03FFC3BFE1DFE0DFF0F6B2FF95FF25FF087F2F37FD1F4CCF6E0FBB83FFC1FFE0FFF4BFFEFFFFFFFFF96E98E945D2B1CAD8E28EF8FBFCDDFFE4FFA2FF60FF20FF22FFB0FFB0FFB0FFB07F22CB5ECF1BF71AD394F38BF9DEDF40FFC7FF41FF70FFDFFF7FF77FFFFFFFF9FCD0FA5CFD67FEDEFF68EF5FF798FFD1FFF7FFFFF8FFBEFF8EFFCFFFEBFFFFFF0E660099B9900CEE4E47967E81BC8080FEFF37FF3FFF3EFF3FFFBFE7FFE3FFBD01D100DB8049208B9E901FE6DF93DF9D37FF6FFF3FFFFFFFDFFFDDFFFDFFFEFF987F10710ECC5E7E0EE8086A8D379CA7FFFDFDFFFFFBFFFDFFFEF7FFF3FFFFDF93F1C0168F12E3D1239A015DE12FF1DCFAFFFEFFFFFFFFFFFF7FEFFFFFFFFF7FA5A7F183FC75BC88C052C09CE2A8F3427CFFF8FFFCFFC0FFE6FFFFFFFFFFFFFEB8971CAC083608FD0879031B0763EF887FFF7FFF3FFF3BFF1FFF1FFF8FFFE7FF4EC470240106C505792AF892E858C4EC9FFFEFFFFFFFFFF9FF78FFFDDFF8FFFE04D10B3A8F99EFCDEF42FF2D2FF48712F0FFF0FFFEDFFE7FFFFFFFFFF77FFF3F445FEB4EF0D6F141F8D9FF2CEF5BF0A67FE77FFFFFFDFFFF3FFE37FEE9FFF9FF3F468FC2FF292F82079D41FF11B9D004FFF0FFD0FFDFFEFFFE3FFF1FFF07FF017F647F40FFCDF413FEAB3EF59EF3E7FDFCFFFCFFFE7FFEE73FE31FF31FF9DFFF9810DFCAFC5B7EA49ED34ECA07F40766EFF0FFF8F7FC7FFD7FFF32FF1EFFEFFF1B16071B5FDB5F193856417FE0970013FF7FFF3FFF7FFF1FFFCF3CFF9FFF3FFFF773F704FF69FF0D7C6B18D74CFB16A4FFB0FFF0FF60FFE03FE0BFE0BBE6FFC3375E775FFC47849E36CF37FE3A6D3C5AFF7BFF28FF00FF00FF00FF10FF02FF87FF73FFACF9DB40F9FC95FC131F7BFEF4FFEFFF47FF43FF43FF11FE03FE7FFFFFFEAAFF44FFF3FFDAFF31FF83E54BE068FFE1FFF9FFCCFFECFBFF00FF0EFF89FF7FA1FF7D3F16AF18998E729C618E46FFFEFBFE63FF41FF41FFE3FFF07FF83FFCFCD9FE60FE41FF58371B17B88F5C4F207FFF7FFF7FFFFFFFFFFFE7FEE77FF77E79A0A32AB3A139088F92CFCCF559E9F8FF7FFF3DFF3DFF0CFF04FF0FFF00FF04FB71FE3CFF39FF0BFF30BFFB1FE8DFE4FFFFFFFFFFFFFFFFFF4BFF87FFC3FF00C5C41E128F2DFF9CFE4AFFB5FFFBF79CFF7FFFFFF7FFFFFFFBFFFFFFFFFFFF7FE252C258CA1AE0CC8406C0D0E3C3FF6CFDFEFDFFFCFFFCFFFEFFFFFFFFDFFFC7071619031B7A0D2A0D477E2D7B50FF07FE0FFF71FFBF7FFF3FFF7FFFFFFFFFFFFC6BFFD4FFE33C2924D920C324E47F2EF9EFF9EFF8EFFEF7FDF7FFE7FFF2FFF2D0477E2F784DB91FBEBBFDBEF594F2DEFF01FF03FFFBF7FFFFF9FFFDFF0DFF0CFBFFFE80FEE2E818C741FFB5FFCD3FECC9FF4EFF00E7F7FFFFFEFF1FFF1FFF1F84FD90EA388BE1B4F1CDF913F911F91AF7FF71FF70FDFCCFFFC7FFE3FFFBFFF860D194112F483A2ABEBCE638FE7AFDFABFFFBFFFFFFFFFFFF0FF80DF80FF83FF5BD21F4BBFDC48010A7E61806E9EF407FFC1FFC0FFC0FFC07F5F3F4E3F4F7FC6663CC15FC0E7C77EBF99FFE2BF29AF5AFFF0FFC0FF7EFF7FFFFEFFFFFFFBE3FE7FB0FF05BF60FDB4FF16CFBFE7D3E7F6FFFFFFFFFF5FFF04FF00FF00FFC0FF00604BB1A0FF17FF2CC39FC307E1E36BFFC1FFFFFFFFFFFF7FFF1CFF1DFF3FFF1F0EFF8038E7C8FF63FFFEFFFFFFF2FEF11FFEEBFFF9FFFFFFFF3FFE3FEF9F7FD7C3874076F006F4F8FC7CFE7DFF7CBFEFEF7EE77EF3BEFBFEFBFC33FE33FE9DFE409BD09CC1991B0E723447155D28C372FF0EFF06FF03FF0FFF19FF0CFF86FF881BEA0F761F323EEEFFD8FFACFFC4FF28FF00FF00FF00FF80FF00FF0EFF06FF02F77C9FFDFF7FFE9FFC0BFFEBEB1AF713FF3FFF0FFF07FF00FF0CFF0CFF0DFF07FFA6F8C87FED1FE53EE17EE9BFEDDFF6FFC2FFE6FFF7FF25FF30FF03FFC9BFFDBFDA970EDF7FF70DFFBD7FC7FE687F48FFDBFF43FFC3FFFBFF19FF11FF80FF80FFDBFF52FF42FFF3FF4DFFF1BF6C7F6CFFB2FFFE8FFFFFFFFFF7FF43FE83FEE1FEBEC9812BF800D77832FC49F09D63A5FF06FF01FF017FC00FF007FE00ED00E63F663F70FFF33FAA5FB72BA8FF807F88FF0FFFBFFF1FFF00FF05FF05FFDD1F3CFCCCFFBEFF1FFFE0FFE5FD677F9FD7C2FF04FF82FF8BFFCFFFFFFFFFFEFFFEFFFE2DFF8C7F0B3F0F0F060302F055F9D1E77FF1FFF8FCFCFEFEEFBBFF43FF20FFD8C5ACA9231FB5921C841FFF086A90337FE0FFE07FE07F601FF00FD007E8E6F82FB8675F61F7B113D13F3841341E44F9F33EF71EFF1FFF0FFF0FFF0FFF0FFF07EB3FFDFB3FF81FB08FDE1ADD7A7D0F7EFF00FF00FF00FF80FFC0FFE0FFE0FFF07F774C7F0E4F87CFC1C7A163F133FBBBFF7BFF00FF00FF12FF09FF1DFF1FFF0FFF76FBFFFFF9FFFFFFFFFFFEFFFFBFFFFFE7FF63FF13FF10FF10FF9BFFADFF88FF3FFF2FFFFEF7FFBFFFFFF7FFF77FFFC3F783FEC3FEC1FFF7FFF7FFF77FF37C99E17DF335FAE1BAEB9DEFD8E3DBCFFF7FF0C3FEF37FC03FEC3FEF9FFF9FFF977FD203E7887E44BE10FFB091FE54FFFBFFF0FF78FF3C3FE01FFF8FFFDFFBFFFFFBC1FC77FE7F7E754D5C2CF80F798736FF03FF01FF7EFB7FED7FFF3FFF9EFF87FFF23FF9FF7E71D5D15EE125FB90FB01FFFFFFFFFF7FFD3FFF7FFF1FFF0FFF1FBFAADF1BFF46F237F061F010B8F23FFFFFC1FFC4FFE7FFFFFBFFFFF8FF89FF257FF30F372C1040275CC60FAD7E28FF26FFF1FFF0FFE8FF80FF80FF00FF00FF00195F3957FC6F7C71E417F11B78C178E480E780F5E0FFF27FF23FF21FF91FFF0F1F805E4185BF40DF49DF340D961F80500F1C0F970F930FD10FC98FE84FF897F8EB79EF536F036F85770937C827C82FBCFE6FFE47FEFFFF2FFE37FC0FFE07FD03FD69FE45FEF57CABBDE7BFEF3CFF16BB50FF78FFFDFF7DFFFCFFFFFFFFE7FFC125FF809D00853A1678B3F87BFFA17F7F07F4C7FCF7FEF3FCFBBEFFFEFFFEFFFE1F4726EF2B1FFFEFF7EFFB77FEFBFEDDFF03FF03FF11FF19FF09FF00FF00FF000707E7EFFBFFFBFFD9FF5DFD4D5D0D5DBFF89FFCDFFFFF7FFFBEFFFEFFFCFF7CDC7DFEE3EFB1CFFFEFD7F7EFF7EFF8F7FF07FF1FFF3FFF4FFF27FF23FF03FF005CFF7E71FCFFFEFDFFFE7FFF3FFF1FFFFF82FFC1FFC0FFE0FFF0FFFCFFF8FF167FFF4FFFA16FA55FDF3FFFCFFEFFFEFEFB7EFF7FFFFDFFFFFFFFFFFCFF1CFF06E7EFF6E3F7E7FFFFFFFBFFEFFFFF3F3FFF01FF80FFF8DF7CEF3EFF1FFF1FFF039BFCD9FCFCBDDE6BB6DF27BD373F1F3FDFFFCFFFF73FFF08FF04FF07FE07FE07AFB6A4BC679E17688FDC18123C63FCF5FFE5FFE5FFE3FF65FF0FFFFF7FEF3DFFFEA5FF64FFA2FF65FF0FF3B13B825247FF3BFFF1FF78FFF8BFF8C7F8C7FCC1FFFF3AFF71C702250E416D042FB27603D2FFF7FFBBFF1FFF1FFF0FFF0DFF00FF00EFE7FEA8FE45FED83FEB1FF91F309E19FF00FF00FF00FF00FF00FF00FF807FC038751CB43CE01F7B377F91AF11FBB039FFCFFE677F03FF03FF01FF41FF00FF00E8AB69AB24D9CCFCC200C160E001E09E0FF807F887FCC7FCC7FCFFFCBFFC9F7C87BC57FC4FEC276F0F70478F14919444FF07FF07FF07FF06FF06FF04FF0CFF049CFEFD21FDBBFDF8FEFDDE734EFB0F69FFC0FFE1FFE0FF70FF37FF17FF03FF01676F272F63A7F9FFFFFF7FFE1FFF87DFFF7EFF3EFF3FFF3FFD3FFFFFFEFFF0FFFFEFFFE7FFF3FFF93BF6FCFBFCD3F8CFFF00FF00FF00FF00DF00FF00FFE00FFC055D000D000403070067C0C0A07177FFFF3CFF9CFF58FFD0FFF0FF79FF70FF7FFFF3FFFFFFFFFC3F9F7FFFFFFFFFFFBFFF04FF07FF3FFF0FFF01FFE5FF7FF27FFFFFFFFFFEF3FFFFFFFFFFFFFFE3EFDDFF33FFF3FFF3CFF9C7FDE277F0BFFC9FFFFDF3FF13EAABFB97E7DCF2F3FFF3FFBF06FF02FF87FFFFFEEF3FFF0FFF3FF99FDF8FCFCE5DFFBDFCFF9F722BF75FEFFF03BF07FF81FFC03FE0DFF0EFF8C7FE0F3F0F5F0FCFCB0F614310D0EA3BAB7DFE07FF03FF01FF01FF01FF017F01FF014CFD0CFD020F030703230303038743CE3EFFFFFFFFFFFFFFFFFFFFFFFFFEFFF680E708A85E04FE09DEC2E684DFB2DF3E83FFD8FFCDFFC1FF40FE80F080F1C07D21AF33FB027F1070ADE21FC67E41AEB1FFE0EFF8F7FCFBFE01FF007F007F10FFF63CDCBE0AFC000C2939D11CE032489E3FC01F601F100F8A0FFF07FE8FFCC7FEA09FF91FEF07FF6A2FCB1FFE1378A3A3FF20F720FB30FF10FFE0FF06FF06FF02F094F0A8F0B4F81FFFC2FE05FFF7FEF3CF7CC73EE33EEB1FFF1FFF3CFF18FF1834D15F4B078E23CD56F27FED26C3177BFF00FF00FF00FF80FFE0FFB0FF30FF380ECF058F85CFE0E5E062F089F801E8F4FF00FF01FFDFFFCFFF00BFF1FF23FF00C1C7C3CFFFFFFFFFFFFFD2F7F3F631F3F17FFDFFFFFFFF00FF00F7FFFFFFFF9FE8DDF3EEFFFBDFFFFFFFFD32FF80FFFC91FFDFFFFFE3FF21FF20FFFFC1FFF1FF6FFE08F7FFFFFFFFFFFFE5DBE132F029FF78FFE0FFBAE71AF71DFF0FFF87FFC3FFE7FF7FEFDFEFFF97FB0FDF8FDFCF0FFA1FFF1FFE1FFFFFFFFFFFFFFFFFFFFFFCF7FFFFFCFBFCF3FFFCCF3DCF79EF703FFFFFFF7BFFBCFFFFFFFF0F9F06CF807F60BF579DC67CCB59A23FFB8FEFC2F7F7FEF7FFF7FF1BFFE1FFFFFFFFF8FF3E2FFFFF2FBB5F57ED0DEFFF8CFFFE77FFC3FFC17FEF3FFF1FFF03FFF4FFECFFE4A7EDEFFF35FBBFF4FFDFFFF5FFFFFEFFFF01B700F300FF80FF03FF0FFF03FE03818381C980CDA8FFDFFFFFFEF6FD7FFDFFF2FF65FF7EFF7EFFB8FFF9FFF95FFCFF9EFFDFFFFFFFFFFFFFFF9F1FEFAFFFE03FE03FF11FF907FF0FFF0FFFC5FFFE41B5EAFBB4FD38B39CFCFFFDFFFDFFFE04FF00FF80FFC0FFFCFFFFFFFFC7FFAFF21F0110733805FE012EF871FFD5FFAFF7FFFFFF5EFFBEFFEEFFDEFFF7FFF1FFE1D47021ACEB04F4177B237B0A3E8A9FFF00FF80FF807FC73FE35FF1FFF0FFFFFEF9FFFC7F983FA61F439FF90DE73F18FF0CFF04FFC2FFE3FFF8FFFCFF3EFFCF05538F85C3E5D38B8733C685FBB9FBCEFFBCFFBCFF3EE33FFF3EFF3EFF1DFF20BCE4BECAE3DFFB9DE7DB3FE13F3FFD7DEF30EF78F73EF13FFC7FFC7FFE1FFF0F597DFFDFEEFBFCEFB3CFF2CFF9F79CFBFF00FF00FF01FFC0FFF0FFFE3CFF3FFFEDFF038383F7FD3F9F6F27D8FBC7DCEBF86FFF0BFFE7FF63FF31FF11FFC87FFBFCE4FFFAFFDFFF4EFFEFFFFF7FFF9FEFFFE3FFE1FF87FF83FFC3FFC1FFC0FBC0EF2FFF7FEF7FE727E346E161C161C0E4FFFFFFFFFFFF98FFFDFFFDFFFFFEE77EFFFCFFFCDF3EC77F1EF3DFBACFBFDFFFEF41FF03FF00FFFAFFF1FFFFFFFFFFFCC3D3E3F7FFFFFF3FFFBFFFBFFFBCFFFFFFDEEFFBEF7BFF7FF7FFDFFFFFFFFFFFFFF7FFDFDFF7E7DFABDF83FF7BE59DEBFFE48FFE0FFE8FFFC7FEE7FEF1FFF0FFFC9C1EF6FFFF27FFB77F9B7FE11FC83CFF01FF01FF00FF00FF00FF20FF7C7FFF1EFF1F3F0F1F0F9FCFFFFFF7FDE7F7E307FCFFFCBFFE87FFC77FFE3FFE1FFE0FEFFF07FFD76BCDFBDFF9E1FFFDEBFDFBFF3EFF3EFF30FF38FF0CFFBC7FF81FF8FFFEFFFEFFFFFEFFFCFDFEF33EFB8EFEFF0FFC27FF03FF03FF01FF01FF01FF00F91AFCBFF4897EEE1FFE0F9F054E0564F6FFFEFFFFFFFFFFFC7FFF7FFD1FFF01C80DC141A009F0D3F127F0E7FA968307FFFF7EFF03F701FFE1FFFFFFFFE3FEF71EA204BC3C81CEF209798B13773A7C5583FF00B000F99EFFFFEFF92FF9FF7DFF2989FF07472028EBD84AFA80462DBA91FF207FC08FF887CCC7E4C3FEC3F7C3F7E07C30BC309A77C31B47334F3C02CC73FF03FF01FF00FF00FF00FF00FF00FF0086FF03C3005907FF07FF03B703DF8183FFFFFFFFFFFFFF6BFF2FFF0DFF07FF033FDCFF3EFFFFFFFFFFFF7FFFEFFFFFFFE7FFF4FFE0FFFBFFF8FFFCFFFEFFF8FF53BCCA7EFE1FF4CFE9570BF7FD83E4DEFF803FC01FE01DF01FF81BFE09DE0DFFC06060E94030889A84DC86DE6C95A08DC37EE33FFF1EFF0CFF04FF007F06FF0FE3F7F1FF173B1F13051F071F0F9FBFFFFF58FF87FF0FFF3FFF37FF03FF1BFF80FCFFFFFFDFFEFFFFFFFF7FFFBFFFDFFFFF7FFF7FFFBFFFDFFFFFFF7FFF3FFF08F7CCE7DAE0FFFEF1FFFAFFFFFFFCFFFFF0FFF8FFFFFFFFFDDFFCEFF8C7FFDFFFE99F857FC23D5FBFEF3FDFBFFFBFE7B93FDF7FFFFFFFFFFFFF7FFFFFFFFFFFFF7B713FD11EF5FFF9FFFAFBC0E8D0FBE0FEC7FEE7CFFBFFFFDFFFC3FF61FFFFFEFD7B95FF3EFF0FF6B76A867FBFDE237D07F807FCEBFEF9FFF5FFFEFFFFFFFFFFCEEFC4FD54BDEB46F07F508310D10485FF00FF00DF00FF00FF00FF807FF8FFFE0107809500E18045802900807EFB6FAAFF80FFF0FF18FF3CF31CFF0EFF0FFE6F87CC151FFB4E77EF779DF186F003F169FFFFFFFFFF7FEDFFF6FFF67FFF7FFFFD8302FFABF610909BA0BFE80BF0A1F7CDFCFFFEFF3EFF0FFF05FF00FF3FFFFFFF3EEBF52992B142D2A2EB527300DD808AE2BFF33FFBBFF3BFEBBFFF9FFB9FFFDFC9B3C054E1DDE37D61FB60DA652CF355FF80FFC0FFE0FFF0FFF8E7F8F7FCF7FE0FDF20ED10498804E8C29439003FC39DFF71FF30FF30FF08FF00FF1CFF06FF00F3F3F8FC7EFE7FFF3F7F3F3F9FFFFFFFFEFFFF7FFF1FFF0FFF27FF11FF10FF3CE07FF6E97FFF7FFB377E3F3F39BFBEF701EF40FFE1FFE7FFFFFBFFF9FFFFFF7917C625F39C7F9C7FE6DFF7CFFEC77FEEFFCEFFC1FFE0FFF0FFF8FFC4FFC3FEE3FFFFFFFFFFBF789FFC35EEFEE7E7F377FF00FFF0FF1CFF04FF00FF10FF18FF88F2FFF9FFFCFFF7FF73F73AFF7FFF7FFFFF0EFF06FF07FF67FF1FFF47FF03FF0FFEFEFFFFFFFFFFFFFFFFFFFF3F7FFFFFFF7EFF7EFF1FFF8FFF81FFE2FFE3FFF9CFB36F57F7EDFFFFFFFFF7FFFB1FFB8FFF3FFF1FFF8DFF70FF30FF00FFA0FFE3FFFBFFFFFFFFFFFFFFFFFFFFFFFFF7FFFFFEFFFFF8FFF8FFFDFFFF7FFFFFFEFFFBB49C72F987E6D7C8C7C482E0A1F0F1FFFFFFFFF7BF7D9FFFDFFFFF0FFF0687C005E064611E70967F797C2A043478BAFFFFFFFFFFF1FF30FF38FFF8FFF007886F23FC68FF97FE27FCACF8FD3F05FF3CFF0FFF0FFF07FFCFFFEFFEFFFE873FD9F2F00E7A2FF67F2FFEAAF98B1C7FFE74FFFEFFBFFF1BFF0BFF81FFC37FE3BFE0F7F0FDBCFE50BE6E7FB57F46BFE15BCCFFFFFF7FFF9FF9FFFDFFFFFFFFFFFFEFE4A0F674F81410CF8027D8CA7C32FCA8FF9EF3DEBFCEBFEEB7FEF7FED9FFF9FFF38C73A63B727FD817CC11FC31BD1495FFFFFF3CFF3CFF9EFF06FF22FF1EFF0EC3CBE340BBE9BADBFEA57FC2BFCFBF76FF10FF00FF00FF00FF20FF00FF00FF007FFFBF67DE3BEE9B74FB3FCBBFE3FFA2FF1CFF1FFF1FFF1FFF07FF03FF60FF37BEBFBFBD3FBE1EBD1FFEE7BEFFFFFF3FFF11FF41FFE1FFF1FFF4FFFEFFFFFFFFBFFFFFFFFF9FFF7FCF3FBF4FFF8FFF5FFFFBFFFCFFFFFF7FFF3FFF07FFE7FFF73FFEAF53FFAFFFFFFFFFFFFFFFFFFFFF3FEE9FFEDF7EF3BEF9DEFFCFFFF7FFFB1FFFFEEEDEA6FFEFF5EFFCFFFC9FDEADFF22FF01FF01FF01FF00FF00FFC0FFE0FFFFBFFF7F7F5FFFF7FFDFFFCF3F3FFFFFFFFF1FFF8FFF83FF83FF03FE03FF03FEF5FEF9DFFCCFFFFFFDDEFD8EDF8689FFF33FF3FFF1FFF1FFF1FFF77FE1FFE0779CFFD39F79DFCDFFBD3F963FC139CFFE7FFFFFF57FFFFFFFFEFFFFFEFFFE0FF979F1F6FA7EC184E1D0E0E7F0E3FDC900FAF8FCFCFFFDFFFFFFFFFF6FFF79FF87A9037FDD0ADCC7FC9D4F3C837B009F0700070403070303818F80CFC0FDE07FFFFBFEFDFBFFFC7874F03721F708C43E1FAC1FB38FF947FF03FFE3FFFBFFFF7FDF6DEF9307A80363B8BC86BCE0C8F86ADFF0FF78FFA8FFBFFEFFDFF1FFF0FFF19973C0E7D700DE941D7BB3FE9D1F1F0CFFEFFF0FFF0FFF8F7FFFFFBFFDFFF7FFFFAA7FECFCD8FD4C3F9FFFA7F2D6609EF8FEECFFFEF9FFFFE6FFF0FFF8FFFEFF033415347751E1CCD0BF819FC44FC1877FC73F710BFF81FF60FF18FF2FFB27FECE60DF39F31F741087D9317E0CC04355FF87FFC0FFF09FFF4FDF0FFF0FFECF7ABFE63F80BF127FCBAFDE4772E71EE779FFFFFFC9FFFFEFFFFFF9FFC8FF58FF6CFF7FFFCBFCC0C63DFF53FFAAFF71FFEDFFFBEFF9CFFCFFFCBFFADF7CFF7DFF3CFF1F9F7FFFB76FD777EF977FC7AD7798FFFBFFD8FF60FF3FFF1FFF0CFF03FF03FFFFFFFFFFEFFFFFBFF7BFFFDFFB8FFBFFFFFFFFFFFFFF7FFFCFFF7FFF1FFFFFFFCEFFF7FFBEFF87FFFFFFFFFFFFFFF3FFFCFFEEFFFFFFFFFFDFFFE7FDFBFFF9FF3FBFFBDF3EFFE7FFF3FD9ECCBEE7DBFF03BF03FF01FF81FF81FFC1FFC1FFE00F990F5E878C8FE84F29253E210FE150FFE0FFE0FFE0FFE0FFF0BFF0FFC0FFC03F1F7F5C371EA39EB97EFDD6BF04FFBEFE0FFF07FF83FFC7FDDFFEFFFFFFFF3FFFEFFE64FE30FE43FA97B83FDC98DE17FCFFFCFFFEFFFEFFF8FFFBFFFBFFFFFF4186000609E3400B04A6051C4444726460FF707F7CCF3EC73FE33FE0FFE0FFF04271AC2D3ADFBD863F029FB61FCA7F5AFC3FFE1FFE1FFE0FFF0FFF07FF03FF07FD37FC90FCDBFCCC7EED3EE73EF2FFE6FFFB7FFD07BC13FE73FF79FF7FD7FFF31F1BA745D332CB3C815B78ADFC947EB0FFFFFFBFFFBFFF7FFFFFFFFFFFFFFF167822F023FE29FC6584518674E7C2FD55F6BFFCDFFFFFF7FFF7FFFDFFFFFFF71FE0AFF95AE00B60A2319E13FF0301FB8821FF367F1FBF0FBF8EFF0FFF8AEF2FFF9CC4AF2D5668C39D6183BF8D746F80F3FF097FFE3BFE39FFBDFFFDCFFFC77ECFFF88FBE23151B8D43AFCBC493C863DDFFFF4BFF8E37CF39FFFEEFFFEFDFFFFFF7F378F4BC3E4E30F39080D27819A8083FF3EFE3FFE7FFFFFFF3CFF04FF00FFE1FBB8E023F041FF6AFF2CDEE5E2FD633DFF03FFC17FF11FF8EFF8FF38FF1EFD0FEF7BF75D3F1DAFF69D73C59363CAF867FFFFFFFFFEFFFEFFFFFFFF3FFF1FFFFFFFF3FEF3F0C1F1E1FFFAF9BAFC99EF04F7FDF3FFF3FF3BFF9BFFFBFFF9FFF9FFE3DD411A089D02CE616D819EE2EEE4277FF0BFF0DFF8FFF8FFFCFFFCFFFEFFFFB19409E0A93545153023AF498FA6EF9EFFC0FFC2FF01FF01FF01FF00FF80FFE0EFBDC7FFFB3CCB7E81CB811C857EFD95FF0FFF07FF03FF81FF81FE81FF03FF03FFEBFEF6FFFBFFFD99EFBEE59E729EB2FFDFFF7FFCBFF6BFFFFFFFEFBFC7BFE3FA52FE1CC096E9AFF05578E97EF51E8AFFF0FFF07FF83FFC1FFC07FC87F4C3F6FD4FF9971C074C8F6E1356CA1BC70FE6FF07FF01FF03FE0FFE877FC17FC1BFFBDFB4FF5CFF52FD497C15671DB3DD1FEFFFFFFFFFFF73FFF3FFDBFFFFFF3BFF9B7E26FCFCFC78FE60FF59FF3CFF3AFFD3FC3FFEA3FE39FF8DFF06FF02FF00FF00FD3EFE21FE39FF8DDF76EF3ADF7CC75E77CF3FEF3FFF17FDF3FFF0FFE07FF07F79EFB9301FBD4FFD0BBDC6198938C15F2FFF9FF7FFFFFFFFFFF7FFF11FF0CFFC0EFC4FF68E3CCC88DE96179D27AE0FC6FFC3FFC3FFE3FFC3FF81FF817E81FE813C997C1C7E7E767E767C7EE1BEFFE67FFFFFFF7FFFFFFFFFFFBFFFFFFFFFFF9FFED4DFC9FCDDFFCEFF387047F8E1FE94CFF1FFF9F7FDFFFEEFFFE1FFF0FFF8FE9B378517AD3B036E87B908320F18270CFE87FFC3FF23FF91FF81FF803FC33FF3FD15EEDEFF6AFFF99FDDCF5E9F7BDF1AFFFFFEBFFFD3FFE3FFE3FFF1EFF9FFFDFCD77D3D7E10BECED28CF2DC8F3E0716F8FFFEFFFEBFFE9FFE9FFFC7FFC7FFC7F6CEF8EEF8A3F9D3FC5D7C65FCE57CE57FFF7FFF7FFF3FFF3FFF7FFF7FFFFFBFE6F57CB93E1C7A623831BF8A6FE5E821FF1F7E87FF817FC0FFC0FFC0FFE08FD8F0483CE745BFC7FF07FE036720472F9CFF03FF83FF83FF01FF01FF01FF1DF3FF8E3E1E725E2243FC99FCFF27F58FE3E9DFFB7FFF7FF7FFFBFFFE7FFFFFFFFFFF0FE267C7FFB72FC323AA8285634CE3E2C7FFC7FFE7FDE3FEC0FFF0FFF7FFFEFF0B400F6793318BBB94728237809F614DFFFFBFFF88FF08FFFCFFFE73FE33FF211FDE5B6B00AFE7F8A98BFC51FF33FEA0FF8DFF8D7FCF1F7E0FFB03FF01FF00E7F7E0BFE97FAFCF0E972B9F0216A93EC0FF80FF80FF80FF00BF00FF00FFC0FFF880CF80F4DCFC4CFE04DFF83C764F7DEEFE3FFE3FFF1FFF0FFF0FFF0FFE1FFF07F0A630E738F01DE97CE83E6CB8D71EFC1FFE9FF7CFFFEFFFFFFEDFFEB7FFEDFF03F06CF40F6D0560055427EC08F8027EFF01FF03FF03FF03FF03FF02FF00FF04FFFE1FFB07DF07FE07BD0707C6D3FE0FFF8FFF8DFF01FF00FF10FF3FFF3FFF00FFEEFFAD3FFD10FF7FD0E0A3FFAFFFF0F8FFF3FFF9FFF8FFFDFF7FFFFFFFFF3E809F841C604BE1E5F0E7B094F3E3FBB23FFFBFFF3FFF0FFF7FFFFFFFFFFFBFFF1F173CA85EAA425800BC3EB21FCA8047FFFDFBFCA7FFF1FFF8FDFCFEFCFCF8FCC38B272C0BDF07F183043B2D1B050747FFC3FFFFFFFFFE8F7DBF7FFF7D7F7F7F3F907C3CE643B86772A7F055B2278055FCFFE0FF00F170FC3EFF7CFF0EFFCFFFF2E6818B5FE02F68B1D718EC1DD116CE07FE47FC63FC637C077C07DE038683C77BC20BF40B60826B933D7304F9383D53F3DFF3CFF95FFD1EFF1EFF3EFF3EFF3EFBDAFCCEF155FDE6FF18FFDC3F4E3F76FFBFFFFFFFFFFF1FFAFFFFFFFF3FFF0BF8A0F093C358F017C587E0CCF026FF8B"));
		System.out.println(decode(""));
	}
	
	
	private static String encode(String input) {

	    long nano1 = System.nanoTime();

	    StringBuilder result = new StringBuilder();
	    int lengthOfInput = input.length();
	    char lastCharacter = input.charAt(0);
	    int lastCharacterCount = 1;

	    // we will go until equal to the length of input and we will do the final flush inside the loop
	    // in this way we will have roughly 90% - 95% performance improvent over nested iteration
	    // but this might take a little more mory as compared to the nested loop iteration
	    for (int index = 1; index <= lengthOfInput; index++) {

	        if (index == lengthOfInput) {
	            // we have already completed everything let us append the final value of index - 1 iteration
	            result.append(lastCharacter).append(lastCharacterCount);
	            break;
	        }

	        char currentCharacter = input.charAt(index);

	        if (lastCharacter == currentCharacter) {

	            lastCharacterCount++;
	        } else {

	            result.append(lastCharacter).append(lastCharacterCount);
	            lastCharacter = currentCharacter;
	            lastCharacterCount = 1;
	        }
	    }

	    long nano2 = System.nanoTime();

	    long result1 = TimeUnit.NANOSECONDS.toMicros(nano2 - nano1);

	    System.out.println("encoding total time taken : nano seconds -> " + result1);

	    return result.toString();
	}
	
	private static String decode(String encoded) {

	    long nano1 = System.nanoTime();

	    StringBuilder result = new StringBuilder();
	    int lengthOfEncodedString = encoded.length();

	    StringBuilder timesToRepeatLastCharacter = new StringBuilder("");
	    char lastCharacter = encoded.charAt(0);

	    for (int index = 1; index <= lengthOfEncodedString; index++) {

	        if (index == lengthOfEncodedString) {
	            // we have reached to the end of encoding ; do the final round
	            // this code looks repeated
	            for (int i = 0; i < Integer.parseInt(timesToRepeatLastCharacter.toString()); i++) {
	                result.append(lastCharacter);
	            }
	            break;
	        }

	        char currentCharacter = encoded.charAt(index);

	        if (Character.isDigit(currentCharacter)) {

	            timesToRepeatLastCharacter.append(currentCharacter);
	        } else {

	            // try parsing the timesToRepeatLastCharacter and get the number of times the character should be repeated
	            for (int i = 0; i < Integer.parseInt(timesToRepeatLastCharacter.toString()); i++) {
	                result.append(lastCharacter);
	            }

	            lastCharacter = currentCharacter;
	            timesToRepeatLastCharacter = new StringBuilder();
	        }

	    }

	    long nano2 = System.nanoTime();

	    long result1 = TimeUnit.NANOSECONDS.toMicros(nano2 - nano1);

	    System.out.println("decoding total time taken : nano seconds -> " + result1);

	    return result.toString();
	}

}
