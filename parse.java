//this part of code is from the code I did it on my own without any help from guide book or friends
//this code is to parse  the first-order logic sentences 
		LinkedList<String> Facts = new LinkedList<String>();
		LinkedList<String> RAW = new LinkedList<String>();
		for(int i =0;i<NOS;i++){
			List<String> GP = RPr(SS[i]);
			if(GP.size()==1){
				String ID =i+"w0";
				List<String> temp=RT(GP.get(0));
					int tnum;
				if(Pd.containsKey(temp.get(0))){
					tnum=Pd.get(temp.get(0));
				}else{
					tnum=pdc;
					Pd.put(temp.get(0), pdc);
					pdc++;
				}
				relation.put(ID, new Predicate(temp.get(0),tnum,(temp.size()-1)));
				for(int k =1;k<temp.size();k++){
					relation.get(ID).isV[k-1]=RV(temp.get(k));
					relation.get(ID).Tname[k-1]=temp.get(k);
				}
				if(RNQ(SS[i])){
					relation.get(ID).n=relation.get(ID).n^true;
					SS[i]=null;
					SS[i]=ID;
				}else{
					SS[i]=null;
					SS[i]=ID;
				}
				Facts.add(SS[i]);
			}else{
				for(int j =0;j<GP.size();j++){
					String ID =i+"w"+j;
					List<String> temp=RT(GP.get(j));
					int tnum;
					if(Pd.containsKey(temp.get(0))){
						tnum=Pd.get(temp.get(0));
					}else{
						tnum=pdc;
						Pd.put(temp.get(0), pdc);
						pdc++;
					}
					relation.put(ID, new Predicate(temp.get(0),tnum,(temp.size()-1)));
					for(int k =1;k<temp.size();k++){
						relation.get(ID).isV[k-1]=RV(temp.get(k));
						relation.get(ID).Tname[k-1]=temp.get(k);
					}
					SS[i]=SS[i].replaceFirst("[a-zA-Z]+\\(.*?\\)", " "+ID+" ");
				}
				String pSS=new String(String.valueOf(SS[i]).getBytes(),"utf-8");
				RAW.add(pSS);
			}
      
		}
    
    public static List<String> RT(String sentence){
		List<String> term=new ArrayList<String>();
		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m = p.matcher(sentence);
		while(m.find()){
			term.add(m.group());
		}
		return term;
	}
	
	public static boolean RV(String v){
		Pattern p = Pattern.compile("[a-z]");
		Matcher m = p.matcher(v);
		return m.matches();
	}
	
	public static boolean RNQ(String n){
		Pattern p = Pattern.compile("~");
		Matcher m = p.matcher(n);
		return m.find();
	}
	
	public static List<String> RPr(String Pdct){
		List<String> UP=new ArrayList<String>();
		Pattern p =Pattern.compile("[a-zA-Z]+\\(.*?\\)");
		Matcher m =p.matcher(Pdct);
		while(m.find()){
			UP.add(m.group());
		}
		return UP;
	}
