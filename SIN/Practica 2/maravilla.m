#!/Aplicaciones/Octave
1;

function cstar=linmach(w,x)
  C=columns(w); cstar=1; max=-inf;
  for c=1:C
    g=w(:,c)'*x;
    if (g>max) max=g; cstar=c; endif; end
endfunction

function [w,E,k]=perceptron(data,b,a,K,iw)
  [N,L]=size(data); D=L-1;
  labs=unique(data(:,L)); C=numel(labs);
  if (nargin<5) w=zeros(D+1,C); else w=iw; end
  if (nargin<4) K=200; end;
  if (nargin<3) a=1.0; end;
  if (nargin<2) b=0.1; end;
  for k=1:K
    E=0;
    for n=1:N
      xn=[1 data(n,1:D)]';
      cn=find(labs==data(n,L));
      er=0; g=w(:,cn)'*xn;
      for c=1:C; if (c!=cn && w(:,c)'*xn+b>g)
    w(:,c)=w(:,c)-a*xn; er=1; end; end
      if (er)
    w(:,cn)=w(:,cn)+a*xn; E=E+1; end; end
    if (E==0) break; end; end
endfunction

function [nerr m]=confus(truelabs,recolabs)
  truelabs=reshape(truelabs,numel(truelabs),1);
  recolabs=reshape(recolabs,numel(recolabs),1);
  N=rows(truelabs); labs=unique([truelabs;recolabs]);
  C=numel(labs); m=zeros(C);
  for n=1:N m(find(labs==truelabs(n)),find(labs==recolabs(n)))++; end
  a=0; for c=1:C a+=m(c,c); end; nerr=N-a;
endfunction

#########################################
### ENTRENAMIENTO
#########################################
    ### Entrenamiento con el 70% de datos
    #####################################
    arg_list = argv();
    file = arg_list{1};
    load(file); [N,L]=size(data); D=L-1;
    ll=unique(data(:,L)); C=numel(ll);
    rand("seed",23); data=data(randperm(N),:);
    [w,E,k]=perceptron(data(1:round(.7*N),:));
    save_precision(4); save(strcat(file,"_percep_w"),"w");
    output_precision(2); 
    
    ### Estimación del error
    ####################################
    load(file);
    [N,L]=size(data); D=L-1;
    ll=unique(data(:,L));
    C=numel(ll); rand("seed",23);
    data=data(randperm(N),:);
    M=N-round(.7*N); te=data(N-M+1:N,:);
    load(strcat(file,"_percep_w")); rl=zeros(M,1);
    for m=1:M
        tem=[1 te(m,1:D)]';
        rl(m)=ll(linmach(w,tem)); end
    [nerr m]=confus(te(:,L),rl)
    
    ### Calculo de intervalo de confianza
    ###################################
    output_precision(2);
    m=nerr/M
    s=sqrt(m*(1-m)/M)
    r=1.96*s
    printf("I=[%.3f, %.3f]\n",m-r,m+r);

########################################
### CALCULO DEL EFECTO DE a
########################################
    load(file); [N,L] = size(data); D=L-1;
    ll=unique(data(:,L)); C=numel(ll);
    rand("seed",23); data = data(randperm(N),:);
    NTr = round(.7*N); M=N-NTr; te = data(NTr+1:N,:);
    printf("#      a   E   k Ete\n");
    printf("#------- --- --- ---\n");
    for a=[.1 1 10 1000 10000 100000]
        [w,E,k]=perceptron(data(1:NTr,:),0.1,a); rl=zeros(M,1);
        for n=1:M
            rl(n)=ll(linmach(w,[1 te(n,1:D)]'));
        end;
        [nerr m] = confus(te(:,L),rl);
        printf("%8.1f %3d %3d %3d\n",a,E,k,nerr);
    end;

########################################
### CALCULO DEL EFECTO DE b
########################################
    load(file); [N,L]=size(data); D=L-1;
    ll=unique(data(:,L)); C=numel(ll);
    rand("seed",23); data=data(randperm(N),:);
    NTr=round(.7*N); M=N-NTr; te=data(NTr+1:N,:);
    printf("#      b   E   k Ete\n");
    printf("#------- --- --- ---\n");
    for b=[.1 1 10 100 1000 10000 100000]
        [w,E,k]=perceptron(data(1:NTr,:),b); rl=zeros(M,1);
        for n=1:M rl(n)=ll(linmach(w,[1 te(n,1:D)]')); end
        [nerr m]=confus(te(:,L),rl);
        printf("%8.1f %3d %3d %3d\n",b,E,k,nerr);
    end;





