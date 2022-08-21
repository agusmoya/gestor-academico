export class Asignatura {

  id:number;
  nombre: string;
  asignaturaPadre: Asignatura;
  asignaturasHijas: Asignatura[]=[];
}
