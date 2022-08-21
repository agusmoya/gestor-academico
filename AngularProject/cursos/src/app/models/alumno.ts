import { Generic } from "./generic";

export class Alumno implements Generic {

  // Para mapear cada atributo de la clase al html de alumnos-form.component.html
  // 1°) importar FormsModule en app.module.ts
  // 2°) [(ngModel)]="alumno.attribute"



  //MISMOS NOMBRES DE ATRIBUTOS QUE EN EL BACKEND. POR DEFECTO SON PUBLIC
  id: number;
  nombre: string;
  apellido: string;
  email: string;
  createAt: string;
  // no agregamos foto porque no lo usamos en el front, pero si usamos fotoHashCode ya que es un getter, los cuales generan los atributos en el json!!!
  fotoHashCode: number;


}
