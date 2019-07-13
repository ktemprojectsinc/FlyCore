package TestCorePlugin;

import me.Ktem.Core.Core;
import me.Ktem.Core.Types.Player;//Импорт игрока
import me.Ktem.Core.api.Command;//Импорт команд
import me.Ktem.Core.api_plugin.Plugin;//Импорт главного класса
import me.Ktem.Ktem_Lang.Ktem_Group;//Импорт групп

public class TestClass extends Plugin  { //Plugin обозначает главный класс плагина
	
	@Override//Включение плагина
	public void onEnable(){
		this.enableCommands();//Включение команд
	}
	
	private void enableCommands(){//Метод включения команд
		//Обычная команда без минимальной группы доступная для всех:
    	new Command("testcommand") {
    		@Override
            public void executeto(final Player player, final String[] args) {//Перезапись abstract метода, Player это CorePlayer
            	player.sendMessage("Command tested");//Сообщение команды
            	return;
            }
    	};
    	//Команда для выполнения которой нужна минимальная группа:
    	Command Command=new Command("testcommandgroup") {//Инициализация переменной Command для последующего использования
            public void executeto(final Player player, final String[] args) {
            	player.sendMessage("Command minimal group "+this.getMinimalGroup().getNameEn()+"§r tested");//this получает класс Command а с помощью getMinimalGroup получаем минимальную группу
            	return;
            }
    	};Command.setMinimalGroup(Ktem_Group.GOLD);
    	new Command("localization") {//Команда для теста локализации
            public void executeto(final Player player, final String[] args) {
            	if(args.length==0){
            	player.sendMessage("§fThis a localization tested command");
            	return;
            	}else{
            		if(args[0].equalsIgnoreCase("getstring")){
            			if(args.length==1){
            				player.sendMessage("§fUse a /localization getstring <key>");return;
            			}else{
            			player.sendMessage(String.format("§fGet string localization for key %s",args[1]));
            			try{
            			Core.sql.getst(player.getName()).sendMessageLocale(args[1]);
            			}catch(Exception e){
            				player.sendMessage("§cError on get string localization key "+args[1]+" error "+e.getMessage());
            			}
            			return;}
            		}else if(args[0].equalsIgnoreCase("getlist")){
            			if(args.length==1){
            				player.sendMessage("§fUse a /localization getlist <key>");return;
            			}else{
            			player.sendMessage(String.format("§fGet list localization for key %s",args[1]));
            			try{
            			Core.sql.getst(player.getName()).sendMessagesLocale(args[1]);
            			}catch(Exception e){
            				player.sendMessage("§cError on get list localization key "+args[1]+" error "+e.getMessage());
            			}return;}
            		}else if(args[0].equalsIgnoreCase("getforlist")){
            			if(args.length<=2){
            				player.sendMessage("§fUse a /localization getforlist <key> <int>");return;
            			}else{
            			player.sendMessage(String.format("§fGet list for localization for key %s",args[1]));
            			try{
            			player.sendMessage(Core.sql.getst(player.getName()).getLanguageList(args[1]).get(Integer.parseInt(args[2])));
            			}catch(Exception e){
            				player.sendMessage("§cError on get list for localization key "+args[1]+" error "+e.getMessage());
            			}return;}
            		}else {
            			player.sendMessage("§fThis a localization tested command");return;
            		}
            	}
            }
    	};
	}

}
