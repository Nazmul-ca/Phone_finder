clear;
hold on;
X = 100;
Y = 100;
cell = 10;
radius = 2;
MINSPEED = 0;
MAXSPEED = 20;
PAUSETIME = 2;
TIME = 900;
N = 1000;
AMOSTRAS = 1;
nodes = zeros(1,6);
nodes_speed = zeros(N,TIME);
nodes_speed_mean = zeros (AMOSTRAS,TIME);
final_pos_x = zeros(AMOSTRAS,N);
final_pos_y = zeros(AMOSTRAS,N);
final_pos_x_mean = zeros(1,N);
final_pos_y_mean = zeros(1,N);

nghbr =zeros(AMOSTRAS,TIME);
a=1;

for a=1:AMOSTRAS
    for i=1:N
        j=1;

        choice=round(1+(4-1)*rand);
        if choice==1
            x = 0;
            y = rand*Y;
         elseif choice==2
            x = rand*X;
            y = 0;
        elseif choice==3
            x = X;
            y = rand*Y;
        elseif choice==4
            x = rand*X;
            y = Y;
        end
        
        
        waypointX = rand*X;
        waypointY = rand*Y;
        speed = MINSPEED+(MAXSPEED - MINSPEED)*rand;
        time = 1;
        node = [x y speed waypointX waypointY time];
        nodes(j,:) =node;
        while time <= TIME
            node = nodes(j,:);
            x = node(1);
            y = node(2);
            speed = node(3);
            waypointX = node(4);
            waypointY = node(5);
            dist = sqrt((x-waypointX)^2+(y-waypointY)^2);
            
            choice=round(1+(4-1)*rand);
            if choice==1
                new_waypointX = 0;
                new_waypointY = rand*Y;
             elseif choice==2
                new_waypointX = rand*X;
                new_waypointY = 0;
            elseif choice==3
                new_waypointX = X;
                new_waypointY = rand*Y;
            elseif choice==4
                new_waypointX = rand*X;
                new_waypointY = Y;
            end

            
            new_speed =  MINSPEED+(MAXSPEED - MINSPEED)*rand;
            time = time + dist/speed + PAUSETIME;
            node = [waypointX waypointY new_speed new_waypointX new_waypointY time];
            j=j+1;
            nodes(j,:) =node;
        end
        
        for k=1:j-1 
            node_ant = nodes(k,:);
            x1 = node_ant(1);
            y1 = node_ant(2);
            speed = node_ant(3);
            x2 = node_ant(4);
            y2 = node_ant(5);
            time_now = node_ant(6);
            node_next = nodes(k+1,:);
            time_next = node_next(6);
            if time_next >TIME
                time_next = TIME;
            end
            times = ceil(time_now):floor(time_next);
            if ~isempty(times)
                nodes_speed(i,times) = speed ;
                
                t=1;
                dist=sqrt((x2-x1)^2+(y2-y1)^2);
                for time_count=times;
                    s=speed*t;
                    q=dist-s;
                    xp = (x1*q + x2*s)/(s+q);
                    yp = (y1*q + y2*s)/(s+q);
                    per_time_posx(i,time_count)=xp;
                    per_time_posy(i,time_count)=yp;
                    t=t+1;
                end
                
            end
        end
        final_pos_x(a,i)= node(1);
        final_pos_y(a,i)= node(2);
     end
    nodes_speed_mean(a,:) = mean(nodes_speed);
    if a==1
        final_pos_x_mean = mean(final_pos_x,1);
        final_pos_y_mean = mean(final_pos_y,1);
    end
    

    basex = per_time_posx(1,:);
    basey = per_time_posy(1,:);
    for node_count=2:N
        for time_count=1:TIME
            if (basex(time_count)-per_time_posx(node_count,time_count))^2+(basey(time_count)-per_time_posy(node_count,time_count))^2<=radius^2
                nghbr(a,time_count)=nghbr(a,time_count)+1;
            end
        end
    end
  
end





z=zeros(X/cell,Y/cell);
for i=1:1:size(final_pos_x_mean,2)
    zx= fix(final_pos_x_mean(i) / cell) ;
    if zx==0 zx=1; end
    zy= fix(final_pos_y_mean(i) / cell) ;
    if zy==0 zy=1; end
    z(zx,zy) = z(zx,zy) +1;
end


figure('Name','RD Avg Speed');     plot (mean(nodes_speed_mean,1),'r'); 
figure('Name','RD Avg Neighbor');  plot (mean(nghbr,1),'r'); 
figure('Name','RD Density 2D');    scatter(final_pos_x_mean,final_pos_y_mean);
figure('Name','RD Density 3D');    surf(z);
grid on 



