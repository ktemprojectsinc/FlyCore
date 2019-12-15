package TestCorePlugin;

import me.Ktem.Core.api_plugin.Plugin;//Импорт главного класса
import me.Ktem.Ktem_Lang.Ktem_Group;//Импорт групп

public class TestClass extends Plugin  { //Plugin обозначает главный класс плагина
	
	@Override
	public void onEnable(){//Включение плагина
		this.enableCommands();//Регистрация команд
	}
	
	private void enableCommands(){//Метод регистрации команд
		//Обычная команда без минимальной группы доступная для всех:
		this.newCmd("testcommand","test2","test3")//Устанавливается имя команды, а также её алиасы (Не ограничено) / Наследуется, для привязки к текущему плагину
			.setAction((gamer,player,cmd,args)->{//Выполнение команды
				player.sendMessage("Command "+cmd+" tested");//Сообщение команды
			})
			.hide()//Скрытие команды и её алиасов из таб листа для подбора команд
			.create();
    	//Команда для выполнения которой нужна минимальная группа:
    	this.newCmd("testcommandgroup")
    		.setMinimalGroup(Ktem_Group.CLAY)//Установка минимальной группы для команды, в данном случае это CLAY
    		.setAction((gamer,player,args)->{
    			player.sendMessage("Command minimal group "+Ktem_Group.CLAY.getNameEn()+"§r tested");
    		})
    		.create();
    	this.newCmd("localization","локализация")
    		.setAction((gamer,player,args)->{
    			if(args.length==0){
                	player.sendMessage("§fThis a localization tested command");
                	return;
    			}
    			switch(args[0].toLowerCase()){
    				default : {
    					player.sendMessage("§fSub command not found, see https://github.com/ktemprojectsinc/FlyCore/blob/master/Dev/CorePlugins/TestCorePlugin/TestClass.java");
    					return;
    				}
	    			case "getstring" : {
	    				if(args.length==1){
	        				player.sendMessage("§fUse a /localization getstring <key>");
	        				return;
	        			}
	        			player.sendMessage(String.format("§fGet string localization for key %s",args[1]));
	        			try{
	        				gamer.sendMessageLocale(args[1]);
	        			}catch(Exception e){
	        				player.sendMessage("§cError on get string localization key "+args[1]+" error "+e.getMessage());
	        			}
	    				break;
	    			}
	    			case "getlist" : {
	    				if(args.length==1){
	        				player.sendMessage("§fUse a /localization getstring <key>");
	        				return;
	        			}
	        			player.sendMessage(String.format("§fGet string localization for key %s",args[1]));
	        			try{
	        				gamer.sendMessageLocale(args[1]);
	        			}catch(Exception e){
	        				player.sendMessage("§cError on get string localization key "+args[1]+" error "+e.getMessage());
	        			}
	        			break;
	    			}
	    			case "getforlist" : {
	    				if(args.length<=2){
	        				player.sendMessage("§fUse a /localization getforlist <key> <int>");
	        				return;
	        			}
	        			player.sendMessage(String.format("§fGet list for localization for key %s",args[1]));
	        			try{
	        				player.sendMessage(gamer.getLanguageList(args[1]).get(Integer.parseInt(args[2])));
	        			}catch(Exception e){
	        				player.sendMessage("§cError on get list for localization key "+args[1]+" error "+e.getMessage());
	        			}
	        			break;
	    			}
    			}
    		})
    		.create();
	}

}
