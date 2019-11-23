package br.imd.ufrn.sistema.telegrambot.command;

public class HelpCommand implements Command {
  @Override
  public String execute(String[] args) {
    return "" +
      "<b>Comandos para manipular o Sistema de Patrimônio IMD:</b>\n" +
      "" + getComandos() + "\n" +
      "" + getObjetos() + "\n" +
      getParametros() + "\n" +
      "\n";
  }

  private String getParametros() {
    return "" +
      "[parametros]:\n" +
      "  -c, --codigo\n" +
      "  -n, --nome\n" +
      //"    para inserir o nome do objeto\n" +
      "  -d, --descricao\n" +
      //"    para inserir a descricao\n" +
      "  -lid, --localizacaoid\n" +
      //"    para inserir a localizacao do objeto pelo id\n" +
      "  -cid, --categoriaid\n";
      //"    para inserir a categoria do objeto pelo id\n";
  }

  public String createHelp() {
    return "" +
      "" +
      "para criar um Bem, p.e. `/create bem -nome PC 01 -descricao Computador em ótimo estado" +
      " -localizacaoid 1 -categoriaid 1`\n" +
    "\tpara criar uma Categoria, p.e. `/create categoria -nome Móvel -descricao Moveis do IMD`";
  }

  public String getObjetos() {
    return "[objeto]: bem, categoria, localizacao\n";
  }

  public String getComandos() {
    return "[comando]: \n" +
      "  /create [objeto] [parâmetros]\n" +
      "  /find [objeto] [parâmetros]\n" +
      "  /delete [objeto] [parâmetros]\n" +
      "  /show [objeto] [parâmetros]\n" +
      "  /help [objeto] [parâmetros]\n";
  }
}
