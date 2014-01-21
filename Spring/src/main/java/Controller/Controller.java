package Controller;

public class Controller
{
	@RequestMapping("home")
	public String paginaPrincipal(){
		return "telaInicial";
	}
	
	@RequestMapping("home2")
	public String paginaPrincipal1(){
		return "home2";
	}
	
	@RequestMapping("cadastro")
	public String cadastroDeProcesso(){
		return "cadastrarProcesso";
	}
	
	@RequestMapping("adicionaProcesso")
	public String adicionaProcesso(HttpServletRequest req){
		try {
			Processo pro = new Processo(); 
				pro.setProcesso(req.getParameter("tipoProcesso"));
				pro.setClasse(req.getParameter("tipoClasse"));
				pro.setArea(req.getParameter("tipoArea"));
				pro.setAssunto(req.getParameter("tipoAssunto"));
				pro.setLocalFisico(req.getParameter("tipoLocal"));
				pro.setDistribuicao(req.getParameter("tipoDistribuicao"));
				pro.setJuiz(req.getParameter("tipoJuiz"));
				pro.setValor(Double.parseDouble(req.getParameter("tipoValor")));
				pro.getParteProcesso().setAutor(req.getParameter("tipoAutor"));
				pro.getParteProcesso().setReu(req.getParameter("tipoReu"));
				pro.getParteProcesso().setVitima(req.getParameter("tipoVitima"));
				pro.getParteProcesso().setTestemunha(req.getParameter("tipoTestemunha"));
				
				
				
				System.out.println(pro.getJuiz()+ "  " + pro.getProcesso() + pro.getValor());
			
				new DAOAdicionaProcesso().adicionaProcesso(pro);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return "redirect:home";
	}
	
	
	@RequestMapping ("pegaProc")
	public void pegaProcesso(Model mod,String id,HttpServletRequest req,HttpServletResponse res){
		id = req.getParameter("id");
		System.out.println(id);
		try {
			
			Processo pro = new DAOAdicionaProcesso().getProcesso(id);
			
			mod.addAttribute("processo", pro);
			
			res.setStatus(200);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

	
	@RequestMapping("result")
	public String result(Model mod,String id,HttpServletResponse res){
		Processo pro = null;
		System.out.println(id);
		
		try {
			
			pro = new DAOAdicionaProcesso().getProcesso(id);
			
			if(id.length() < 23){
			
			}else if(pro == null){
				System.out.println(pro);
				res.setStatus(204);
				
			}else{
				res.setStatus(200);
				mod.addAttribute("processo", pro);
				System.out.println("ae - "+ pro.getValor() + " - " + pro.getValorString());
				return "result";
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return null;
	}
	
	@RequestMapping("rodape")
	public String rodape(){
		return "Rodape";
	}
	
	@RequestMapping("teste")
	public String testando(){
		return "teste";
	}
	
	@RequestMapping("cadastro-movimento")
	public String mov(){
		return "registrarMovimento";
	}
	
	@RequestMapping("adicionaMovimentacao")
	public void alteraMov(String id,HttpServletRequest req,HttpServletResponse res){
		try {
			id = String.valueOf(id);
			
			System.out.println(id);
			Movimentacao mov = new  Movimentacao();
			mov.setData(req.getParameter("tipoData"));
			mov.setDesc(req.getParameter("tipoDesc"));
			
			System.out.println("to aquii" + mov.getData() + " -  " + mov.getDesc());
			
			new DAOAdicionaProcesso().adicionaMovimentacao(id, mov);
			
			res.setStatus(200);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}