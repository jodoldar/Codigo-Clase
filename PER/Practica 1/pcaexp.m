#!/usr/bin/octave -qf

if (nargin!=7)
    printf('Usage: pcaexp.m <trdata> <trlabels> <tedata> <telabels> <mink> <stepk> <maxk>\n');
    exit(1);
end

arg_list=argv();
trdata=arg_list{1};
trlabs=arg_list{2};
tedata=arg_list{3};
telabs=arg_list{4};
mink=str2num(arg_list{5});
stepk=str2num(arg_list{6});
maxk=str2num(arg_list{7});
load(trdata);
load(trlabs);
load(tedata);
load(telabs);

%% Fragmento de codigo para calcular la tasa de fallos de PCA
for val=mink:stepk:maxk
  [Mp,Wp] = pca(X,val);
  X2 = Wp'*X;
  Y2 = Wp'*Y;
  err=knn(X2,xl,Y2,yl,1);
  printf('%d\t%f\n',val,err);
end

%% Fragmento de codigo para calcular la tasa de fallos de LDA
%for val=mink:stepk:maxk
%  Wl = lda(X,xl,val);
%  X2 = Wl'*X;
%  Y2 = Wl'*Y;
%  err=knn(X2,xl,Y2,yl,1);
%  printf('%d\t%f\n',val,err);
%end

%% Fragmento de codigo para calcular la tasa de fallos original
%for val=mink:stepk:maxk
%  err=knn(X,xl,Y,yl,1);
%  printf('%d\t%f\n',val,err);
%end