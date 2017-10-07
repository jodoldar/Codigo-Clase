importdata('./data/hart/ts.dat',' ',4);
ts = ans.data;
importdata('./data/hart/tslabels.dat',' ',4);
tslabels = ans.data;
importdata("./data/hart/tr.dat",' ',4);
tr = ans.data;
importdata("./data/hart/trlabels.dat",' ',4);
trlabels = ans.data;

hold on
scatter(tr(trlabels==1,1),tr(trlabels==1,2),'x');
scatter(tr(trlabels==2,1),tr(trlabels==2,2),'s');


res=svmtrain(trlabels,tr,'-t 2 -c 1');

alfa = res.sv_coef;
clase = trlabels(res.sv_indices,1);
equis = tr(res.sv_indices,:);
mult = alfa.*clase.*equis;
sigma = sum(mult);
sigma0  = clase(1)-sigma*equis(1,:)';

svFun = @(x1) (sigma*x1' + sigma0);
%plot(svFun(min(equis(:,1):1:max(equis(:,1)))));
plot(svFun(equis(:,:)));
hold off