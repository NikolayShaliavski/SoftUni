using Microsoft.EntityFrameworkCore.Migrations;

namespace StudentSystemData.Migrations
{
    public partial class ResourcesTableRenamed : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Recources_Courses_CourseId",
                table: "Recources");

            migrationBuilder.DropPrimaryKey(
                name: "PK_Recources",
                table: "Recources");

            migrationBuilder.RenameTable(
                name: "Recources",
                newName: "Resources");

            migrationBuilder.RenameIndex(
                name: "IX_Recources_CourseId",
                table: "Resources",
                newName: "IX_Resources_CourseId");

            migrationBuilder.AddPrimaryKey(
                name: "PK_Resources",
                table: "Resources",
                column: "Id");

            migrationBuilder.AddForeignKey(
                name: "FK_Resources_Courses_CourseId",
                table: "Resources",
                column: "CourseId",
                principalTable: "Courses",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Resources_Courses_CourseId",
                table: "Resources");

            migrationBuilder.DropPrimaryKey(
                name: "PK_Resources",
                table: "Resources");

            migrationBuilder.RenameTable(
                name: "Resources",
                newName: "Recources");

            migrationBuilder.RenameIndex(
                name: "IX_Resources_CourseId",
                table: "Recources",
                newName: "IX_Recources_CourseId");

            migrationBuilder.AddPrimaryKey(
                name: "PK_Recources",
                table: "Recources",
                column: "Id");

            migrationBuilder.AddForeignKey(
                name: "FK_Recources_Courses_CourseId",
                table: "Recources",
                column: "CourseId",
                principalTable: "Courses",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
