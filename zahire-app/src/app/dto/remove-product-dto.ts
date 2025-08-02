export class RemoveProductDto {
  id: string;
  searchCriterion: string;

  constructor(id: string, searchCriterion: string) {
    this.id = id;
    this.searchCriterion = searchCriterion;
  }
}
